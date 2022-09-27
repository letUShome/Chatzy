package web.slack.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import web.slack.config.handlers.CustomLoginSuccessHandler;
import web.slack.config.handlers.CustomLogoutSuccessHandler;
import web.slack.config.jwt.JwtAuthenticationFilter;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.service.CustomOauth2UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomLogoutSuccessHandler logoutSuccessHandler;
    private final CustomLoginSuccessHandler loginSuccessHandler;
    private final CustomOauth2UserService customOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll() // 우선 전체허용
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                // .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .oauth2Login()
                // .successHandler(loginSuccessHandler)
                .userInfoEndpoint().userService(customOauth2UserService);


        return httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class).build();
    }

}
