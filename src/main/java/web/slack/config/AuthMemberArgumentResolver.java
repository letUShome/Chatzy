package web.slack.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import web.slack.config.annotation.AuthMember;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthMember authMemberAnnotation = parameter.getParameterAnnotation(AuthMember.class);
        if(!authMemberAnnotation.required()){
            return null;
        }

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        assert request != null;
        log.info(request.getHeader("Authorization"));
        log.info(jwtTokenProvider.getMemberInfo(request.getHeader("Authorization")));
        Member member = memberRepository.findByEmail(jwtTokenProvider.getMemberInfo(request.getHeader("Authorization"))).get();

        return member;
    }
}
