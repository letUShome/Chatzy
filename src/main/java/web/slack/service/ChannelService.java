package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.ChannelDTO;
import web.slack.domain.entity.Channel;
import web.slack.domain.repository.ChannelRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;

    public List<ChannelDTO> findChannels() {
        List<Channel> channels = channelRepository.findAll();
        List<ChannelDTO> channelDTOs = new ArrayList<>();

        for (Channel channel : channels) {
            ChannelDTO channelDTO = ChannelDTO.builder()
                                                .id(channel.getId())
                                                .workspaceId(channel.getWorkspaceId())
                                                .name(channel.getName())
                                                .type(channel.getType().toString())
                                                .teammate(channel.getTeammate())
                                                .build();
            channelDTOs.add(channelDTO);
        }
        return channelDTOs;
    }

    public ChannelDTO findChannel(String channelId) {
        Channel channel = channelRepository.findById(channelId).orElseThrow(() -> new IllegalArgumentException("없는 채널입니다"));
        ChannelDTO channelDTO= ChannelDTO.builder()
                                .id(channel.getId())
                                .workspaceId(channel.getWorkspaceId())
                                .name(channel.getName())
                                .type(channel.getType().toString())
                                .teammate(channel.getTeammate())
                                .build();
        return channelDTO;
    }

    public ChannelDTO addChannel(ChannelDTO channelDTO) {
        Channel channel = channelDTO.toEntity();
        String channelId = channelRepository.save(channel).getId();
        Channel savedChannel = channelRepository.findById(channelId).orElseThrow(() -> new IllegalArgumentException("없는 채널입니다"));
        return savedChannel.toDTO();
    }
}
