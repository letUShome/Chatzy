package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.ChatroomResponseDTO;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.domain.entity.Message;
import web.slack.service.ChatroomService;
import web.slack.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private final ChatroomService chatroomService;
    private final SimpMessageSendingOperations template;

    @MessageMapping("/message")
    public void sendMessage(@Payload MessageRequestDTO messageRequestDTO) {
        String sender = messageRequestDTO.getSender();
        ChatroomResponseDTO chatroomResponseDTO = chatroomService.findChatroom(messageRequestDTO.getChatroomId());

        if(chatroomResponseDTO.getType().getValue().equals("GROUP")) {
            Message message = messageService.addMessage(messageRequestDTO);
            template.convertAndSend("/topic/channel/" + messageRequestDTO.getChatroomId(), message.toDTO());
        }
        else {
            List<String> teammates = chatroomResponseDTO.getTeammate();
            for(String teammate : teammates) {
                if(teammate.equals(sender)) {
                    Message message = messageService.addMessage(messageRequestDTO);
                    template.convertAndSend("/topic/direct/" + messageRequestDTO.getChatroomId(), message.toDTO());
                }
            }
        }
    }

    @GetMapping("/chatrooms/{chatroomId}/message")
    public List<MessageResponseDTO> messageList(@PathVariable String chatroomId) {
        return messageService.findMessageList(chatroomId);
    }

    @DeleteMapping("/chatrooms/{chatroomId}/message")
    public String messageRemove(@PathVariable String chatroomId) {
        return messageService.removeMessage(chatroomId);
    }
}
