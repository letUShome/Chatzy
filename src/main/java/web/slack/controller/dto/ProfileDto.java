package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileDto {
    private String nickname;
    private String email;
    private String memberId;
    private String workspaceId;

    @Builder
    public ProfileDto(String nickname, String email, String memberId, String workspaceId) {
        this.nickname = nickname;
        this.email = email;
        this.memberId = memberId;
        this.workspaceId = workspaceId;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void updateEmail(String email){
        this.email = email;
    }

    public void updateMemberId(String memberId){ this.memberId = memberId; }

    public void updateWorkspaceId(String workspaceId){ this.workspaceId = workspaceId; }
}