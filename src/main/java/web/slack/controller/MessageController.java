package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.ChannelDTO;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.domain.entity.Message;
import web.slack.service.ChannelService;
import web.slack.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private final ChannelService channelService;
    private final SimpMessageSendingOperations template;

    @MessageMapping("/message")
    public void sendMessage(@Payload MessageRequestDTO messageRequestDTO) {
        String sender = messageRequestDTO.getSender();
        ChannelDTO channelDTO = channelService.findChannel(messageRequestDTO.getChannelId());

        if(channelDTO.getType().getValue().equals("GROUP")) {
            Message message = messageService.addMessage(messageRequestDTO);
            template.convertAndSend("/topic/channel/" + messageRequestDTO.getChannelId(), message.toDTO());
        }
        else {
            List<String> teammates = channelDTO.getTeammate();
            for(String teammate : teammates) {
                if(teammate.equals(sender)) {
                    Message message = messageService.addMessage(messageRequestDTO);
                    template.convertAndSend("/topic/direct/" + messageRequestDTO.getChannelId(), message.toDTO());
                }
            }
        }
    }

    @GetMapping("/channels/{channelId}/message")
    public List<MessageResponseDTO> messageList(@PathVariable String channelId) {
        return messageService.findMessageList(channelId);
    }

    @DeleteMapping("/channels/{channelId}/message")
    public String messageRemove(@PathVariable String channelId) {
        return messageService.removeMessage(channelId);
    }
}
