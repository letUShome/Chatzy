package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.MessageType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageRequestDTO {
    private MessageType type;
    private String channelId;
    private String sender;
    private String message;
    private String time;

    @Builder
    public MessageRequestDTO(String type, String channelId, String sender, String message) {
        LocalDateTime time = LocalDateTime.now();
        this.type = MessageType.valueOf(type);
        this.channelId = channelId;
        this.message = message;
        this.sender = sender;
        this.time = time.toString();
    }
}
