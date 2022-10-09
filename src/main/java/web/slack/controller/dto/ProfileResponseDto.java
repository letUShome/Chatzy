package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Profile;

@Getter
public class ProfileResponseDto {

    private String id;
    private String name;
    private String nickname;
    private String role;

    @Builder
    public ProfileResponseDto(Profile entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.role = entity.getRole();
    }

}
