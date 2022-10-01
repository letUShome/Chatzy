package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Channel;
import web.slack.domain.entity.ChannelType;

import java.util.HashMap;

@Getter
@NoArgsConstructor
public class ChannelDTO {
    private String workspaceId;
    private String name;
    private ChannelType type;

    private HashMap<String, String> userlist = new HashMap<String, String>();

    @Builder
    public ChannelDTO(String workspaceId, String name, String type) {
        this.workspaceId = workspaceId;
        this.name = name;
        this.type = ChannelType.valueOf(type);
    }

    public Channel toEntity() {
        return Channel.builder()
                .name(this.getName())
                .type(this.getType())
                .workspaceId(this.getWorkspaceId())
                .build();
    }
}
