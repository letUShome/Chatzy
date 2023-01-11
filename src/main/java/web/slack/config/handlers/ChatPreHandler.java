package web.slack.config.handlers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Profile;
import web.slack.domain.repository.ProfileRepository;

import javax.management.Notification;
import java.util.Objects;

@RequiredArgsConstructor
@Component
@Slf4j
public class ChatPreHandler implements ChannelInterceptor {

    private final ProfileRepository profileRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // 헤더 토큰 얻기
        String authorHeader = String.valueOf(headerAccessor.getNativeHeader("Authorization"));
        //헤더에서 토큰 얻었을 시, 대괄호가 쳐져있어서 이를 잘라내는 코드
        String token = authorHeader.replaceAll("\\[(.*?)\\]", "$1");

        if(jwtTokenProvider.validateToken(token)) {
            return message;
        }
        else {
            throw new JwtException("유효한 토큰이 아닙니다");
        }
    }
}
