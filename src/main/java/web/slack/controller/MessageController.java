package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.controller.dto.ChatroomResponseDTO;
import web.slack.controller.dto.MemberResponseDto;
import web.slack.controller.dto.MessageRequestDTO;
import web.slack.controller.dto.MessageResponseDTO;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Message;
import web.slack.domain.entity.Profile;
import web.slack.service.ChatroomService;
import web.slack.service.MemberService;
import web.slack.service.MessageService;
import web.slack.service.ProfileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private final ChatroomService chatroomService;
    private final MemberService memberService;
    private final ProfileService profileService;
    private final SimpMessageSendingOperations template;
    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/message")
    public void sendMessage(@Payload MessageRequestDTO messageRequestDTO, @Header String Authorization) {
        ChatroomResponseDTO chatroomDTO = chatroomService.findChatroom(messageRequestDTO.getChatroomId());
        String memberId = memberService.findMemberByEmail(jwtTokenProvider.getMemberInfo(Authorization)).getId();
        String workspaceId = chatroomDTO.getWorkspaceId();
        String chatroomType = chatroomDTO.getType().getValue();

        Profile sender = profileService.findByMemberWorkspace(memberId, workspaceId);

        if(chatroomType.equals("GROUP")) {
            Message message = messageService.addMessage(messageRequestDTO, sender);
            MessageResponseDTO messageResponseDTO = message.toDTO();
            template.convertAndSend("/topic/channel/" + messageRequestDTO.getChatroomId());
        }
        else {
            List<String> teammates = chatroomDTO.getTeammate();
            for(String teammate : teammates) {
                if(teammate.equals(sender.getId())) {
                    Message message = messageService.addMessage(messageRequestDTO, sender);
                    template.convertAndSend("/topic/direct/" + messageRequestDTO.getChatroomId(), message.toDTO());
                }
            }
        }
    }

    @GetMapping("/chatroom/{chatroomId}/message")
    public List<MessageResponseDTO> messageList(@PathVariable String chatroomId) {
        return messageService.findMessageList(chatroomId);
    }

    @DeleteMapping("/chatroom/{chatroomId}/message")
    public String messageRemove(@PathVariable String chatroomId) {
        return messageService.removeMessage(chatroomId);
    }
}
