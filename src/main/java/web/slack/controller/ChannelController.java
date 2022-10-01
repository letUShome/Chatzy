package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.ChannelDTO;
import web.slack.service.ChannelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("/")
    public ChannelDTO channelAdd(@RequestBody ChannelDTO channelDTO) {
        return channelService.addChannel(channelDTO);
    }

    @GetMapping("/")
    public List<ChannelDTO> channelList() {
        return channelService.findChannels();
    }

    @GetMapping("/{channelId}")
    public ChannelDTO channelDetails(@PathVariable String channelId) {

    }
}
