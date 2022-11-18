package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.ChatroomRequestDTO;
import web.slack.controller.dto.ChatroomResponseDTO;
import web.slack.service.ChatroomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
public class ChatroomController {
    private final ChatroomService chatroomService;

    @PostMapping()
    public ChatroomResponseDTO channelAdd(@RequestBody ChatroomRequestDTO chatroomRequestDTO) {
        return chatroomService.addChatroom(chatroomRequestDTO);
    }

    @GetMapping()
    public List<ChatroomResponseDTO> channelList() {
        return chatroomService.findChatrooms();
    }

    @GetMapping("/{chatroomId}")
    public ChatroomResponseDTO channelDetails(@PathVariable String chatroomId) {
        return chatroomService.findChatroom(chatroomId);
    }

    @DeleteMapping("/{chatroomId}")
    public String chatroomRemove(@PathVariable String chatroomId) {
        return chatroomService.removeChatroom(chatroomId);
    }
}
