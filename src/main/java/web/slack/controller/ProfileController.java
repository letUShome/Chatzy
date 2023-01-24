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

    @GetMapping("/{profile_id}")
    public ProfileResponseDto findById(@PathVariable String profile_id) {
        return profileService.findById(profile_id);
    }

    @PatchMapping("/{profile_id}")
    public ProfileResponseDto updateProfile(@PathVariable String profile_id, @RequestBody ProfileRequestDto profileRequestDto){
        return profileService.updateProfile(profile_id, profileRequestDto);
    }

    @DeleteMapping("/{profile_id}")
    public String deleteProfile(@PathVariable String profile_id){
        profileService.deleteProfile(profile_id);
        return "프로필이 삭제되었습니다. id = "+profile_id;
    }

}