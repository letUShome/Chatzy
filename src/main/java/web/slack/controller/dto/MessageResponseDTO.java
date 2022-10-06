package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Message;
import web.slack.domain.entity.MessageType;

@Getter
@NoArgsConstructor
public class MessageResponseDTO {
    private String id;

    private String channelId;

    private Member sender;

    private String context;

    private MessageType type;

    private Boolean readFlag;

    @Builder
    public MessageResponseDTO(Message entity) {
        this.id = entity.getId();
        this.channelId = entity.getChannel();
        this.sender = entity.getSender();
        this.context = entity.getContext();
        this.type = entity.getType();
        this.readFlag = entity.getReadFlag();
    }
}
