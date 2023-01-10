package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.controller.dto.ProfileResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Document(collection = "Message")
public class Message {
    @Id
    private String id;

    private String context;

    private Profile sender;

    private String chatroom;

    private MessageType type;

    private String date;

    private Boolean readFlag;

    @Builder
    public Message(String context, String chatroom, MessageType type) {
        this.context = context;
        this.chatroom = chatroom;
        this.type = type;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a HH:mm"));;
        this.readFlag = false;
    }

    public void updateSender(Profile profile) {
        this.sender = profile;
    }

    public MessageResponseDTO toDTO() {
        return MessageResponseDTO.builder()
                                .entity(this)
                                .build();
    }
}
