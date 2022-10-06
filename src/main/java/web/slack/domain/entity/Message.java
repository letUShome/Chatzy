package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import web.slack.controller.dto.MessageResponseDTO;

@Getter
@NoArgsConstructor
@Document(collection = "Message")
public class Message {
    @Id
    private String id;

    private String context;

    private Member sender;

    private String channel;

    private MessageType type;

    private Boolean readFlag;

    @Builder
    public Message(String context, Member sender, String channel, MessageType type) {
        this.context = context;
        this.sender = sender;
        this.channel = channel;
        this.type = type;
        this.readFlag = false;
    }

    public MessageResponseDTO toDTO() {
        return MessageResponseDTO.builder()
                                .entity(this)
                                .build();
    }
}
