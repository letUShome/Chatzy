package web.slack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.slack.domain.entity.EmailToken;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailTokenService emailTokenService;
    private final MemberRepository memberRepository;

    public boolean verifyEmail(String token)  {
        // 이메일 토큰을 찾아옴
        EmailToken findEmailToken = emailTokenService.findByIdAndExpirationDateAfterAndExpired(token);

        Optional<Member> findMember = memberRepository.findById(findEmailToken.getEmail());
        findEmailToken.setTokenToUsed();

        Member member = findMember.get();
        member.setVerified(true);
        return true;

    }
}
