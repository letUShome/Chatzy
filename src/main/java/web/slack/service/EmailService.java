package web.slack.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.EmailRequestDto;
import web.slack.domain.entity.EmailToken;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
//@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService{

    private final EmailTokenService emailTokenService;
    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String FROM_MAIL;

    public void sendEmail(EmailRequestDto mailDto) {
        List<String> emailList = mailDto.getEmail();

        for (int i=0; i<emailList.size(); i++) {
            try {
                MailHandler mailHandler = new MailHandler(mailSender);
                mailHandler.setTo(mailDto.getEmail().get(i));
                mailHandler.setFrom(FROM_MAIL);
                mailHandler.setSubject("SLACK 초대가 들어왔습니다.");
                String htmlContent = "<p>" + "초대~" +"<p> <img src='cid:sample-img'>";
                mailHandler.setText(htmlContent, true);
                // 이미지 삽입
                mailHandler.setInline("sample-img", "static/sample1.jpg");

                mailHandler.send();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
