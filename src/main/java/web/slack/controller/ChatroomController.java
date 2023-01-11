package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.ChatroomRequestDTO;
import web.slack.controller.dto.ChatroomResponseDTO;
import web.slack.service.ChatroomService;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspaces")
public class ChatroomController {
    private final ChatroomService chatroomService;

    @PostMapping("/{workspaceId}/chatroom")
    public ChatroomResponseDTO channelAdd(@PathVariable String workspaceId, @RequestBody ChatroomRequestDTO chatroomRequestDTO) {
        return chatroomService.addChatroom(workspaceId, chatroomRequestDTO);
    }

    @GetMapping("/{workspaceId}/chatroom")
    public List<ChatroomResponseDTO> channelList(@PathVariable String workspaceId) {
        return chatroomService.findChatrooms(workspaceId);
    }


    @GetMapping("/{workspaceId}/chatroom/{chatroomId}")
    public ChatroomResponseDTO channelDetails(@PathVariable String chatroomId, @PathVariable String workspaceId) {
        return chatroomService.findChatroom(workspaceId, chatroomId);
    }

    @DeleteMapping("/{workspaceId}/chatroom/{chatroomId}")
    public String chatroomRemove(@PathVariable String chatroomId, @PathVariable String workspaceId) {
        return chatroomService.removeChatroom(workspaceId, chatroomId);
    }
}
