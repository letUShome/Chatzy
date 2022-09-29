package web.slack.config.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String token = jwtTokenProvider.createToken(findMemberId(oAuth2User));
        response.setHeader("Authentication", token);
        getRedirectStrategy().sendRedirect(request, response, makeRedirectUrl());
    }

    private String makeRedirectUrl(){
        return UriComponentsBuilder.fromUriString("http://localhost:3000/").build().toUriString();
    }

    private String findMemberId(OAuth2User oAuth2User){
        String email = oAuth2User.getAttribute("email");
        Member member = memberRepository.findByEmail(email).get();
        return member.getId();
    }

}
