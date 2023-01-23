package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Member;

import java.util.List;
import java.util.Map;

@Getter
public class MemberResponseDto {
    private String id;
    private String name;
    private String email;

    //profile-workspace id맵을 return함
    private List<Map<String, String>> profiles;

    @Builder
    public MemberResponseDto(Member entity, List<Map<String, String>> memberWorkspace) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.profiles = memberWorkspace;
    }
}
