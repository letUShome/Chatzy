package web.slack.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.MailDto;
import web.slack.domain.entity.EmailToken;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.EmailTokenRepository;
import web.slack.domain.repository.MemberInviteRepository;
import web.slack.domain.repository.MemberRepository;
import web.slack.domain.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "efubslack@gmail.com";
    private final MemberRepository memberRepository;
    private final MemberRepository memberInviteRepository;
    private final EmailTokenRepository emailTokenRepository;

    public void mailSend(MailDto mailDto) {
        String email = mailDto.getAddress();
        String workspaceId = mailDto.getWorkspaceId();
        Member entity = memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("유저가 없습니다"));
        EmailToken emailToken = EmailToken.createEmailToken(email, workspaceId);
        emailTokenRepository.save(emailToken);


        try {
            //String auth = getAuthCode(6);

            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(mailDto.getAddress());
            // 보내는 사람
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject("워크스페이스 초대 이메일 인증");
            // HTML Layout

            String htmlContent = "<p><a href='http://localhost:8080/confirm-email?token=" + emailToken.getId() + "'>FakeSlack 워크스페이스 이메일 인증</a></p>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();
            memberInviteRepository.save(entity);

            System.out.println("발급받은 토큰 Id : "+emailToken.getId());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // 유효한 토큰 가져오기
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String emailTokenId) {

        Optional<EmailToken> emailToken = emailTokenRepository.findByIdAndExpirationDateAfterAndExpired(emailTokenId, LocalDateTime.now(), false);

        //토큰이 없다면 예외 발생
        return emailToken.orElseThrow(() -> new IllegalArgumentException("토큰이 없습니다. "));
    }


}