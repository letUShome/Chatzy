package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Profile;
import web.slack.domain.entity.Workspace;

@Getter
@NoArgsConstructor
public class ProfileRequestDto {
    private String nickname;
    private String memberId;
    private String workspaceId;

    @Builder
    public ProfileRequestDto(String nickname, String memberId, String workspaceId){
        this.nickname = nickname;
        this.memberId = memberId;
        this.workspaceId = workspaceId;
    }

    public Profile toEntity(){
        return Profile.builder()
                .nickname(this.getNickname())
                .memberId(this.getMemberId())
                .workspaceId(this.getWorkspaceId())
                .build();

    }
}