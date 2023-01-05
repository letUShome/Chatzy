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
    private String name;

    private ChatroomType type;

    private List<String> teammate;


    public Chatroom toEntity(String workspaceId) {
        return Chatroom.builder()
                .name(this.getName())
                .type(this.getType())
                .workspaceId(workspaceId)
                .build();
    }

    public void updateChannelDTO(List<String> teammate) {
        this.teammate = teammate;
    }
}
