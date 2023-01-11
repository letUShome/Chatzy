package web.slack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.controller.dto.LogInRequestDto;
import web.slack.controller.dto.OauthLogInRequestDto;
import web.slack.controller.dto.MemberResponseDto;
import web.slack.controller.dto.SignUpRequestDto;
import web.slack.domain.entity.BodyMessage;
import web.slack.domain.entity.GoogleCode;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.GoogleCodeRepository;
import web.slack.domain.repository.MemberRepository;
import web.slack.domain.repository.ProfileRepository;
import web.slack.util.ResponseMessage;
import web.slack.util.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    private final GoogleCodeRepository codeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String generateCode(Member member){
        GoogleCode googleCode = GoogleCode.builder()
                .member(member)
                .code(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000)))
                .createdAt(LocalDateTime.now().plusMinutes(3))
                .build();
        codeRepository.save(googleCode);

        return "?id=" + googleCode.getId() + "&code=" + googleCode.getCode();
    }

    public String signUp(SignUpRequestDto signUpRequestDto) {
        Member member = Member.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .build();
        memberRepository.save(member);
        return "회원가입에 성공하였습니다.";
    }

    public boolean isCorrectMember(LogInRequestDto logInRequestDto) {
        Optional<Member> member = memberRepository.findByEmail(logInRequestDto.getEmail());
        if(member.isEmpty()){
            return false;
        }
        else if(!Objects.equals(member.get().getPassword(), logInRequestDto.getPassword())){
            return false;
        }
        return true;
    }

    public List<Map<String, String>> findMemberWorkspace(Member member){
        List<Map<String, String>> profiles = profileRepository.findByMemberId(member.getId());
        log.info(profiles.toString());
        return profiles;
    }

    public ResponseEntity<?> googleLogIn(OauthLogInRequestDto requestDto) {
        GoogleCode code = codeRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인증입니다."));

        codeRepository.deleteById(code.getId());

        log.info(code.getMember().getEmail());

        BodyMessage bodyMessage = new BodyMessage();
        HttpHeaders headers = new HttpHeaders();

        log.info(code.getCreatedAt().toString());
        log.info(String.valueOf(code.getCreatedAt().isAfter(LocalDateTime.now())));

        if (code.getCreatedAt().isBefore(LocalDateTime.now())) {
            bodyMessage.setMessage(ResponseMessage.LOGIN_FAIL);
            return new ResponseEntity<>(bodyMessage, HttpStatus.BAD_REQUEST);
        } else {
            bodyMessage.setStatus(StatusEnum.OK);
            bodyMessage.setMessage(ResponseMessage.LOGIN_SUCCESS);
            headers.set("Authorization", jwtTokenProvider.createAccessToken(code.getMember().getId()));
            headers.set("refresh-token", jwtTokenProvider.createRefreshToken(code.getMember().getEmail()));
        }

        return new ResponseEntity<>(bodyMessage, headers, HttpStatus.OK);
    }
    public MemberResponseDto findMemberByEmail(String email) {
         Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 메일로 가입한 유저가 없습니다: " + email));

         return member.toDTO();
    }
}
