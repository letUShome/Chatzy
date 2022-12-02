package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Message;
import web.slack.domain.entity.MessageType;
import web.slack.domain.entity.Profile;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDTO {
    private String id;

    private String chatroomId;

    private Profile sender;

    private String context;

    private MessageType type;

    private String date;

    private Boolean readFlag;

    @Builder
    public MessageResponseDTO(Message entity) {
        this.id = entity.getId();
        this.chatroomId = entity.getChatroom();
        this.sender = entity.getSender();
        this.context = entity.getContext();
        this.type = entity.getType();
        this.date = entity.getDate();
        this.readFlag = entity.getReadFlag();
    }
}
