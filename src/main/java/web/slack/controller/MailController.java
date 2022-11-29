package web.slack.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.slack.controller.dto.MailDto;
import web.slack.service.MailService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(MailDto mailDto) { // 메일 발송
        mailService.mailSend(mailDto);
    }


}