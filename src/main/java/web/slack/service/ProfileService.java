package web.slack.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.ProfileRequestDto;
import web.slack.controller.dto.ProfileResponseDto;
import web.slack.domain.entity.Profile;
import web.slack.domain.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) { this.profileRepository = profileRepository; }

    public ProfileResponseDto buildResponseDto(Profile entity) { return new ProfileResponseDto(entity); }

    // 프로필 생성
    @Transactional
    public ProfileResponseDto saveProfile(ProfileRequestDto profileRequestDto){
        Profile profile = Profile.builder()
                .name(profileRequestDto.getName())
                .nickname(profileRequestDto.getNickname())
                .email(profileRequestDto.getEmail())
                .build();

        profileRepository.save(profile);

        return buildResponseDto(profile);
    }

    // 프로필 전체 조회
    @Transactional
    public List<ProfileResponseDto> findAllProfileList(){
        List<Profile> profileList = profileRepository.findAll();
        List<ProfileResponseDto> profileResponseDtoList = new ArrayList<>();
        for (Profile profile : profileList){
            ProfileResponseDto profileResponseDto = new ProfileResponseDto(profile);
            profileResponseDtoList.add(profileResponseDto);
        }
        return profileResponseDtoList;
    }

    // 프로필 상세 조회
    @Transactional
    public ProfileResponseDto findById(String id){
        Profile entity = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필이 없습니다. id " + id));
        return new ProfileResponseDto(entity);
    }

    // 프로필 수정
    @Transactional
    public ProfileResponseDto updateProfile(String id, Profile profile){
        Optional<Profile> profileData = profileRepository.findById(id);

        if(profileData.isPresent()) {
            Profile _profile = profileData.get();
            _profile.setName(profile.getName());
            _profile.setNickname(profile.getNickname());
            _profile.setEmail(profile.getEmail());
            return new ProfileResponseDto(profileRepository.save(_profile));
        }
        else{
            return new ProfileResponseDto(profile);
        }
    }

    // 프로필 삭제
    public void deleteProfile (String id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필이 없습니다. id = " + id));
        profileRepository.delete(profile);
    }

}
