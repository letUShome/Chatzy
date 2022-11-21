package web.slack.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.MailDto;
import web.slack.controller.dto.ProfileDto;
import web.slack.controller.dto.ProfileRequestDto;
import web.slack.controller.dto.ProfileResponseDto;
import web.slack.domain.entity.Profile;
import web.slack.service.ProfileService;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile")
    public String dispProfile() {
        return "profile";
    }


}
