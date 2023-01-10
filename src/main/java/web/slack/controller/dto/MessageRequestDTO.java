package web.slack.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MessageRequestDTO {

    @JsonProperty("chatroom_id")
    private String chatroomId;

    private String context;

    private MessageType type;

    @Builder
    public MessageRequestDTO(String type, String channelId, String context) {
        this.type = MessageType.valueOf(type);
        this.chatroomId = channelId;
        this.context = context;
    }

    public Message toEntity() {
        return Message.builder()
                    .chatroom(this.getChatroomId())
                    .context(this.getContext())
                    .type(this.getType())
                    .build();
    }
}
