package web.slack.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.MailDto;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberInviteRepository;
import web.slack.domain.repository.MemberRepository;
import web.slack.domain.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "efubslack@gmail.com";
    private final MemberRepository memberRepository;
    private final MemberRepository memberInviteRepository;



    public void mailSend(MailDto mailDto) {
        String email = mailDto.getAddress();
        String workspaceId = mailDto.getWorkspaceId();
        Member entity = memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("유저가 없습니다"));

        try {
            //String auth = getAuthCode(6);

            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(mailDto.getAddress());
            // 보내는 사람
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject(mailDto.getTitle());
            // HTML Layout

            String htmlContent = "<p>" + mailDto.getMessage() +"</p>" +
                    "<p><a href='http://localhost:8080/workspace/" + workspaceId + "'>초대받은 워크스페이스 링크</a></p>";
            mailHandler.setText(htmlContent, true);
            // 첨부 파일
            //mailHandler.setAttach("newTest.txt", "static/originTest.txt");
            // 이미지 삽입
            //mailHandler.setInline("sample-img", "static/sample1.jpg");


            mailHandler.send();
            //entity.updateAuthKey(auth); // DB에 유저의 authkey 저장
            memberInviteRepository.save(entity);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    public String mailConfirm(String email, String auth) {
//        LocalDateTime now = LocalDateTime.now();
//
//        Member entity = memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("유저가 없습니다"));
//        if(entity==null) {
//            return "없는 유저입니다.";
//        }
//        String realAuth = entity.getAuthKey();
//        if(realAuth.equals(auth)) {
//            entity.updateAuthKey(null);
//            return "email : "+email;
//        } else {
//            return "something went wrong!";
//        }
//
//    }

//    private String getAuthCode(int size) {
//        Random random = new Random();
//        StringBuffer buffer = new StringBuffer();
//        int num = 0;
//        while(buffer.length() < size) {
//            num = random.nextInt(10);
//            buffer.append(num);
//        }
//        return buffer.toString();
//    }
}
