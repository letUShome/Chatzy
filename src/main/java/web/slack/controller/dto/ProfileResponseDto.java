package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Profile;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {

    private String id;
    private String nickname;
    private String email;
    private String memberId;
    private String workspaceId;

    @Builder
    public ProfileResponseDto(Profile entity){
        this.id = entity.getId();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.memberId = entity.getMemberId();
        this.workspaceId = entity.getWorkspaceId();
    }

    public Profile toEntity() {
        return Profile.builder()
                .id(this.id)
                .nickname(this.nickname)
                .workspaceId(this.workspaceId)
                .memberId(this.memberId)
                .email(this.email)
                .build();
    }

}