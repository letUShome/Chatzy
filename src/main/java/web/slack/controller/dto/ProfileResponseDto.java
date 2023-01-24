package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Profile;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {

    private String id;
    private String image;
    private String nickname;
    private String memberId;

    @Builder
    public ProfileResponseDto(Profile entity){
        this.id = entity.getId();
        this.image = entity.getImage();
        this.nickname = entity.getNickname();
        this.memberId = entity.getMemberId();
    }

    public Profile toEntity() {
        return Profile.builder()
                .id(this.id)
                .nickname(this.nickname)
                .image(this.image)
                .memberId(this.memberId)
                .build();
    }

}