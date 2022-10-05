package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.slack.controller.dto.ChannelDTO;
import web.slack.domain.entity.Channel;
import web.slack.service.ChannelService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    private final ChannelService channelService;

    @GetMapping("/room")
    public String rooms(Model model) {
        return "room";
    }

    @GetMapping("/room/enter/{channelId}")
    public String roomDetail(Model model, @PathVariable String channelId) {
        model.addAttribute("channelId", channelId);
        return "roomDetail";
    }

}
