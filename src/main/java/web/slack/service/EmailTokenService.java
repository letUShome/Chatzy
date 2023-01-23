package web.slack.service;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.slack.domain.entity.EmailToken;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.EmailTokenRepository;
import web.slack.domain.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailTokenService {
    private final EmailTokenRepository emailTokenRepository;

    public String createEmailToken(String email, String workspaceId) {
        //1.토큰 생성

        //2.profile entity에 token 저장

        //3.emailService쪽으로 토큰 전달
        return "later";
    }

    // 유효한 토큰 가져오기
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String emailTokenId) {

        Optional<EmailToken> emailToken = emailTokenRepository.findByIdAndExpirationDateAfterAndExpired(emailTokenId, LocalDateTime.now(), false);

        //토큰이 없다면 예외 발생
        return emailToken.orElseThrow(() -> new IllegalArgumentException("토큰이 없습니다. "));
    }
}