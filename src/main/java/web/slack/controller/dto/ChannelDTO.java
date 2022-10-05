package web.slack.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Channel;
import web.slack.domain.entity.ChannelType;
import web.slack.domain.entity.Member;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChannelDTO {
    private String id;

    @JsonProperty("workspace_id")
    private String workspaceId;

    private String name;
    private ChannelType type;
    private List<String> teammate;

    @Builder
    public ChannelDTO(String id, String workspaceId, String name, String type, List<String> teammate) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.name = name;
        this.type = ChannelType.valueOf(type);
        this.teammate = teammate;
    }

    public Channel toEntity(List<Member> members) {
        return Channel.builder()
                .name(this.getName())
                .type(this.getType())
                .workspaceId(this.getWorkspaceId())
                .teammate(members)
                .build();
    }
}
