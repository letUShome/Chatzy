package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.domain.entity.Message;
import web.slack.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chat")
public class MessageController {
    private final MessageService messageService;
    private final SimpMessageSendingOperations template;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload MessageRequestDTO messageRequestDTO) {
        log.info(messageRequestDTO.getContext());

        Message message = messageService.addMessage(messageRequestDTO);
        template.convertAndSend("/channel/" + message.getChannel(), message.toDTO());
    }

    @GetMapping("/channel/{channelId}")
    public List<MessageResponseDTO> messageList(@PathVariable String channelId) {
        return messageService.findMessageList(channelId);
    }
}
