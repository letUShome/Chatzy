package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileRequestDto {

    private String id;
    private String name;
    private String nickname;
    private String role;

    @Builder public ProfileRequestDto(String id, String name, String nickname, String role){
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
    }
}
