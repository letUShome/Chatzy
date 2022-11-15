package web.slack.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Member;

@Getter
public class MemberResponseDto {
    private String id;
    private String name;
    private String email;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }
}
