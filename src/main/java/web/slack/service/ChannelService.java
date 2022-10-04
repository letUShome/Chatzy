package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.ChannelDTO;
import web.slack.domain.entity.Channel;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.ChannelRepository;
import web.slack.domain.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final MemberRepository memberRepository;

    public List<ChannelDTO> findChannels() {
        List<Channel> channels = channelRepository.findAll();
        List<ChannelDTO> channelDTOs = new ArrayList<>();

        for (Channel channel : channels) {
            ChannelDTO channelDTO = ChannelDTO.builder()
                                                .id(channel.getId())
                                                .workspaceId(channel.getWorkspaceId())
                                                .name(channel.getName())
                                                .type(channel.getType().toString())
                                                .teammate(createIdList(channel.getTeammate()))
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
                                .teammate(createIdList(channel.getTeammate()))
                                .build();
        return channelDTO;
    }

    @Transactional
    public ChannelDTO addChannel(ChannelDTO channelDTO) {
        List<Member> members = findMemberById(channelDTO.getTeammate());

        Channel channel = channelDTO.toEntity(members);
        String channelId = channelRepository.save(channel).getId();

        Channel savedChannel = channelRepository.findById(channelId).orElseThrow(() -> new IllegalArgumentException("없는 채널입니다"));
        List<String> memberIds = createIdList(members);
        return savedChannel.toDTO(memberIds);
    }

    //나중에 memberservice로 옮길 내용
    public List<Member> findMemberById(List<String> memberIds) {
        List<Member> members = new ArrayList<>();
        for(String memberId : memberIds) {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다"));
            members.add(member);
        }
        return members;
    }

    public List<String> createIdList(List<Member> members) {
        List<String> memberIds = new ArrayList<>();
        for(Member member : members) {
            String memberId = member.getId();
            memberIds.add(memberId);
        }
        return memberIds;
    }
}
