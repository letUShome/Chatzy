package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.MailDto;
import web.slack.service.EmailService;
import web.slack.service.EmailTokenService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/confirm")
    public ResponseEntity<Boolean> viewConfirmEmail(@RequestParam String token) {
        Boolean result = emailService.verifyEmail(token);
        System.out.println("워크스페이스 초대 메일 인증이 완료 되었습니다. token = "+token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
