package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.domain.entity.Message;
import web.slack.domain.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<MessageResponseDTO> findMessageList(String channelId) {
        List<Message> messages = messageRepository.findAllByChannel(channelId);
        List<MessageResponseDTO> messageResponseDTOS = new ArrayList<>();

        for(Message message : messages) {
            messageResponseDTOS.add(message.toDTO());
        }
        return messageResponseDTOS;
    }

    public Message addMessage(MessageRequestDTO messageRequestDTO) {
        Message message = messageRequestDTO.toEntity();
        return messageRepository.save(message);
    }
}
