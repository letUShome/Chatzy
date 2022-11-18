package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import web.slack.controller.dto.ChatroomResponseDTO;

import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "Chatroom")
public class Chatroom {

    @Id
    private String id;

    private String workspaceId;

    private ChatroomType type;

    private String name;

    private List<Member> teammate;

    @Builder
    public Chatroom(String workspaceId, ChatroomType type, String name, List<Member> teammate) {
        this.workspaceId = workspaceId;
        this.type = type;
        this.name = name;
        if(teammate!=null) {
            this.teammate = teammate;
        }
    }

    public void updateTeammate(List<Member> members) {
        this.teammate = members;
    }

    public ChatroomResponseDTO toDTO() {
        return ChatroomResponseDTO.builder().chatroom(this).build();
    }
}
