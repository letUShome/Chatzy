package web.slack.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import web.slack.domain.repository.MemberRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 토큰을 받아온다
        String accessToken = jwtTokenProvider.resolveAccessToken((HttpServletRequest) request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);

        log.info(String.valueOf(jwtTokenProvider.validateToken(accessToken)));

        // 유효한 토큰인지 확인
        if(accessToken != null && jwtTokenProvider.validateToken(accessToken)){
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        }

        chain.doFilter(request, response);
    }
}
