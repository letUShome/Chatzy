package web.slack.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Chatroom;
import web.slack.domain.entity.ChatroomType;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatroomRequestDTO {
    @JsonProperty("workspace_id")
    private String workspaceId;

    private String name;

    private ChatroomType type;

    private List<String> teammate;

    @Builder
    public ChatroomRequestDTO(String workspaceId, String name, String type, List<String> teammate) {
        this.workspaceId = workspaceId;
        this.name = name;
        this.type = ChatroomType.valueOf(type);
        this.teammate = teammate;
    }

    @Builder
    public ChatroomRequestDTO(String workspaceId, String name, String type) {
        this.workspaceId = workspaceId;
        this.name = name;
        this.type = ChatroomType.valueOf(type);
    }

    public Chatroom toEntity() {
        return Chatroom.builder()
                .name(this.getName())
                .type(this.getType())
                .workspaceId(this.getWorkspaceId())
                .build();
    }

    public void updateChannelDTO(List<String> teammate) {
        this.teammate = teammate;
    }
}
