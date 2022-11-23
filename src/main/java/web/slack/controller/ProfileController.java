package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.ProfileRequestDto;
import web.slack.controller.dto.ProfileResponseDto;
import web.slack.domain.entity.Profile;
import web.slack.service.ProfileService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping()
    public ProfileResponseDto saveProfile(@RequestBody ProfileRequestDto profileRequestDto){
        return profileService.saveProfile(profileRequestDto);
    }

    @GetMapping()
    public List<ProfileResponseDto> findAllProfileList(){
        return profileService.findAllProfileList();
    }

    @GetMapping("/{id}")
    public ProfileResponseDto findById(@PathVariable String id) {
        return profileService.findById(id);
    }

    @PostMapping("/{id}")
    public ProfileResponseDto updateProfile(@PathVariable String id, @RequestBody Profile profile){
        return profileService.updateProfile(id, profile);
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable String id){
        profileService.deleteProfile(id);
        return id;
    }

}