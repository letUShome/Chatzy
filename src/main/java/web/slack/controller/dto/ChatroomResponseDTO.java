package web.slack.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Chatroom;
import web.slack.domain.entity.ChatroomType;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Profile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatroomResponseDTO {
    private String id;

    @JsonProperty("workspace_id")
    private String workspaceId;

    private String name;

    private ChatroomType type;

    private List<String> teammate;

    @Builder
    public ChatroomResponseDTO(Chatroom chatroom) {
        this.id = chatroom.getId();
        this.workspaceId = chatroom.getWorkspaceId();
        this.name = chatroom.getName();
        this.type = chatroom.getType();
        if(chatroom.getTeammate()!=null) this.teammate = extractProfileId(chatroom.getTeammate());
    }

    public List<String> extractProfileId(List<Profile> teammate) {
        List<String> profileIds = new ArrayList<>();
        for(Profile profile : teammate) {
            profileIds.add(profile.getId());
        }
        return profileIds;
    }
}
