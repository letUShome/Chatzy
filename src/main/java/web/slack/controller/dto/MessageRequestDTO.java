package web.slack.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Message;
import web.slack.domain.entity.MessageType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageRequestDTO {

    @JsonProperty("chatroom_id")
    private String chatroomId;

    private String sender;

    private String context;

    private MessageType type;

    @Builder
    public MessageRequestDTO(String type, String channelId, String sender, String context) {
        this.type = MessageType.valueOf(type);
        this.chatroomId = channelId;
        this.context = context;
        this.sender = sender;
    }

    public Message toEntity(Member member) {
        return Message.builder()
                    .sender(member)
                    .chatroom(this.getChatroomId())
                    .context(this.getContext())
                    .type(this.getType())
                    .build();
    }
}
