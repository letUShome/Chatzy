package web.slack.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import web.slack.controller.dto.ChannelDTO;

import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "Channel")
public class Channel {

    @Id
    private String id;

    private String workspaceId;

    private ChannelType type;

    private String name;

    private List<Member> teammate;

    @Builder
    public Channel(String workspaceId, ChannelType type, String name, List<Member> teammate) {
        this.workspaceId = workspaceId;
        this.type = type;
        this.name = name;
        this.teammate = teammate;
    }

    public ChannelDTO toDTO(List<String> memberIds) {
        return ChannelDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .type(this.getType().getValue())
                .workspaceId(this.getWorkspaceId())
                .teammate(memberIds)
                .build();
    }
}
