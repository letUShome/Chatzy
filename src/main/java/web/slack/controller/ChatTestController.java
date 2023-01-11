package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.slack.service.ChatroomService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ws")
public class ChatTestController {
    private final ChatroomService chatroomService;

    @GetMapping()
    public String rooms(Model model) {
        return "room";
    }

    @GetMapping("/enter/{chatroomId}")
    public String roomDetail(Model model, @PathVariable String chatroomId) {
        model.addAttribute("chatroomId", chatroomId);
        return "roomDetail";
    }

}
