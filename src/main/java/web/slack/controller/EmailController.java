package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.slack.service.EmailService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/confirm")
    public ResponseEntity<Boolean> viewConfirmEmail(@RequestParam String token) {
        Boolean result = emailService.verifyEmail(token);
        System.out.println("워크스페이스 초대 메일 인증이 완료 되었습니다. token = "+token);
        if (result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


}
