package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Profile;
import web.slack.domain.entity.Workspace;

@Getter
public class ProfileRequestDto {

    private String id;
    private String nickname;
    private String email;
    private String memberId;
    private String workspaceId;

    @Builder
    public ProfileRequestDto(String id, String nickname, String email, String memberId, String workspaceId){
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.memberId = memberId;
        this.workspaceId = workspaceId;
    }

    public Profile toEntity(){
        return Profile.builder()
                .nickname(this.getNickname())
                .email(this.getEmail())
                .memberId(this.getMemberId())
                .workspaceId(this.getWorkspaceId())
                .build();

    }
}