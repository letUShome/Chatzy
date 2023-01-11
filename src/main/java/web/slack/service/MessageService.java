package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Message;
import web.slack.domain.entity.Profile;
import web.slack.domain.repository.MemberRepository;
import web.slack.domain.repository.MessageRepository;
import web.slack.domain.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    public List<MessageResponseDTO> findMessageList(String chatroomId) {
        List<Message> messages = messageRepository.findAllByChatroomOrderByDate(chatroomId);
        List<MessageResponseDTO> messageResponseDTOS = new ArrayList<>();

        for(Message message : messages) {
            messageResponseDTOS.add(message.toDTO());
        }
        return messageResponseDTOS;
    }

    public Message addMessage(MessageRequestDTO messageRequestDTO, Profile profile) {
        Message message = messageRequestDTO.toEntity();
        message.updateSender(profile);
        return messageRepository.save(message);
    }

    public String removeMessage(String chatroomId) {
        try{
            messageRepository.deleteMessagesByChatroom(chatroomId);
            return "success";
        }
        catch(Exception e) {
            return "failed: " + e.getMessage();
        }
    }
}
