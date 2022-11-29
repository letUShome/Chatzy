package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "Profile")
public class Profile {

    @Id
    private String id;

    private String nickname;

    private String email;

    private String memberId;

    private String workspaceId;

    @Builder
    public Profile(String id,String nickname, String email, String memberId, String workspaceId){
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.memberId = memberId;
        this.workspaceId = workspaceId;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setMemberId(String memberId){
        this.memberId = memberId;
    }

    public void setWorkspaceId(String workspaceId) { this.workspaceId = workspaceId; }
}