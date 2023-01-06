package web.slack.config.handlers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Profile;
import web.slack.domain.repository.ProfileRepository;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ChatPreHandler implements ChannelInterceptor {

    private final ProfileRepository profileRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // 헤더 토큰 얻기
        String authorHeader = String.valueOf(headerAccessor.getNativeHeader("Authorization"));
        // 토큰 자르기 fixme 토큰 자르는 로직 validate 로 리팩토링
        String token = authorHeader.replaceAll("\\[(.*?)\\]", "$1");

        if(jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            Member member = (Member)auth.getPrincipal();
            Profile profile = profileRepository.findProfileByMemberId(member.getId()).orElseThrow(() -> new IllegalArgumentException("워크스페이스에 속하지 않은 유저입니다."));
            MessageRequestDTO messageDTO = (MessageRequestDTO) message.getPayload();

            if(Objects.equals(profile.getId(), messageDTO.getSender())) return message;
            else throw new MessageDeliveryException("잘못된 유저로부터 온 메세지입니다");
        }
        else {
            return message;
        }

    }
}