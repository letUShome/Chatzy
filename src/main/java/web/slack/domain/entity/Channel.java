package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "Channel")
public class Channel {

    @Id
    private String id;

    private String workspaceId;

    private ChannelType type;

    private String name;

    @Builder
    public Channel(String workspaceId, ChannelType type, String name) {
        this.workspaceId = workspaceId;
        this.type = type;
        this.name = name;
    }
}
