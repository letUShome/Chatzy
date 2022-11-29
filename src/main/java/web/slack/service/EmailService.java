package web.slack.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.MailDto;
import web.slack.domain.entity.EmailToken;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import java.util.Optional;
//@Slf4j
@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class EmailService {

    private final EmailTokenService emailTokenService;
    private final MemberRepository memberRepository;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "efubslack@gmail.com";

    public Boolean verifyEmail(String token) {
        EmailToken emailToken = emailTokenService.findByIdAndExpirationDateAfterAndExpired(token);

        Member findMember = memberRepository.findByEmail(emailToken.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));

        // 이메일 성공 인증 로직 구현
        emailToken.setTokenToUsed();
        findMember.updateTokenFlag();
        return true;

    }

}
