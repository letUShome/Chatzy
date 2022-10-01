package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/channel")
public class MessageController {
    private final SimpMessageSendingOperations template;

    @MessageMapping("/{channel_id}/sendMessage")
    public void sendMessage(@Payload MessageRequestDTO messageRequestDTO) {
        log.info(messageRequestDTO.getMessage());
        template.convertAndSend("/sub/channel/" + messageRequestDTO.getChannelId(), messageRequestDTO);
    }
}
