package web.slack.config.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // authentication 정보가 있으면 로그아웃 로직 실행
        if(authentication != null && authentication.getDetails() != null){
            try{
                request.getHeader("Authorization");
                //TODO: redis에 있는 refresh token 제거
                //TODO: Header에 jwt 토큰 제거
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("http://localhost:3000");
    }
}
