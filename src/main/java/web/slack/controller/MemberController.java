package web.slack.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.oauth2.sdk.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;
import web.slack.service.CustomOauth2UserService;
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
