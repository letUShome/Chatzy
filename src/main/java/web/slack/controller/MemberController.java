package web.slack.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.slack.config.annotation.AuthMember;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.controller.dto.LogInRequestDto;
import web.slack.controller.dto.SignUpRequestDto;
import web.slack.controller.dto.MemberResponseDto;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Message;
import web.slack.domain.entity.ResponseMessage;
import web.slack.domain.entity.StatusEnum;
import web.slack.domain.repository.MemberRepository;
import web.slack.service.CustomOauth2UserService;
import web.slack.service.MemberService;
import web.slack.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOauth2UserService oauth2UserService;
    private final MemberRepository memberRepository;
    private final RedisService redisService;
    private final MemberService memberService;

    @GetMapping("/current")
    public MemberResponseDto loadLoginUser(@AuthMember Member member){
        return new MemberResponseDto(member);
    }

    @PostMapping("/signup")
    public String createMember(@RequestBody SignUpRequestDto signUpRequestDto){
        return memberService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LogInRequestDto logInRequestDto){
        Message message = new Message();
        if(!memberService.isCorrectMember(logInRequestDto)){
            // message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage(ResponseMessage.LOGIN_FAIL);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        else{
            message.setStatus(StatusEnum.OK);
            message.setMessage(ResponseMessage.LOGIN_SUCCESS);
            HttpHeaders httpHeaders = new HttpHeaders();
            String memberId = memberRepository.findByEmail(logInRequestDto.getEmail()).get().getId();
            httpHeaders.set("Authorization", jwtTokenProvider.createAccessToken(memberId));
            httpHeaders.set("refresh-token", jwtTokenProvider.createRefreshToken(memberId));
            return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
        }
    }

    @PostMapping("/postman")
    public String postmanTest(@RequestBody Map<String, Object> attribute, HttpServletResponse response){
        String memberId = oauth2UserService.loadUserPostman(attribute);
        String token = jwtTokenProvider.createAccessToken(memberId);
        String refreshToken = jwtTokenProvider.createRefreshToken(memberId);
        response.setHeader("Authorization", token);
        response.setHeader("refresh-token", refreshToken);
        return "Response with header using HttpServletResponse";
    }

    @PostMapping("/reissue")
    public String reissue(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{

        String memberId = jwtTokenProvider.getMemberInfo(request.getHeader("refresh-token"));
        String memberEmail = memberRepository.findById(memberId).get().getEmail();

        log.info(redisService.getValues(memberEmail));
        log.info(request.getHeader("refresh-token"));

        if(Objects.equals(redisService.getValues(memberEmail), request.getHeader("refresh-token"))){
            String accessToken = jwtTokenProvider.createAccessToken(memberId);
            response.setHeader("Authorization", accessToken);
        }
        return "Response with header using HttpServletResponse";
    }


}
