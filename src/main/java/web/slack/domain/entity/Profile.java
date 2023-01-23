package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import web.slack.controller.dto.ProfileResponseDto;

@Getter
@NoArgsConstructor
@Document(collection = "Profile")
public class Profile {
    // TODO 다른 프로필 입력사항들은 추후에 업데이트

    @Id
    private String id;

    private String nickname;

    private String image;

    private String memberId;

    private String workspaceId;

    private Authority auth;

    @Builder
    public Profile(String id, String nickname, String image, String memberId, String workspaceId, Authority auth){
        this.id = id;
        this.nickname = nickname;
        this.image = image;
        this.memberId = memberId;
        this.workspaceId = workspaceId;
        this.auth = auth;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void updateImage(String image){
        this.image = image;
    }

    public void updateAuth(Authority auth) {
        this.auth = auth;
    }

    public ProfileResponseDto toDTO() {
        return ProfileResponseDto.builder().entity(this).build();
    }
}