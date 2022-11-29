package web.slack.config.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.domain.repository.MemberRepository;
import web.slack.service.RedisService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisService redisService;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // authentication 정보가 있으면 로그아웃 로직 실행
        if(authentication != null && authentication.getDetails() != null){
            try{
                String memberEmail = jwtTokenProvider.getMemberInfo(request.getHeader("Authorization"));
                //TODO: redis에 있는 refresh token 제거
                //TODO: Header에 jwt 토큰 제거
                redisService.deleteValues(memberEmail);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("http://localhost:3090/google");
    }
}
