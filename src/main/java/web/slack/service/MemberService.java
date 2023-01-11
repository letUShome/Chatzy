package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.LogInRequestDto;
import web.slack.controller.dto.MemberResponseDto;
import web.slack.controller.dto.SignUpRequestDto;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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

    public MemberResponseDto findMemberByEmail(String email) {
         Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 메일로 가입한 유저가 없습니다: " + email));

         return member.toDTO();
    }
}
