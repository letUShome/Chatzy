package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.slack.service.EmailService;
import web.slack.service.EmailTokenService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;
    private final EmailTokenService emailTokenService;

    @GetMapping("/confirm-email")
    public ResponseEntity<Boolean> viewConfirmEmail(@RequestParam String token) {
        Boolean result = emailService.verifyEmail(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
