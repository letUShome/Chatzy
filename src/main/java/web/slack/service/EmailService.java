package web.slack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.slack.domain.entity.EmailToken;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailTokenService emailTokenService;
    private final MemberRepository memberRepository;

    public Boolean verifyEmail(String token) {
        EmailToken findEmailToken = emailTokenService.findByIdAndExpirationDateAfterAndExpired(token);

        Member findMember = memberRepository.findByEmail(findEmailToken.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));

        // 이메일 성공 인증 로직 구현
        findEmailToken.setTokenToUsed();
        findMember.setVerified(true);
        return true;

    }

}
