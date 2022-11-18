package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.ChatroomRequestDTO;
import web.slack.controller.dto.ChatroomResponseDTO;
import web.slack.domain.entity.Chatroom;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.ChatroomRepository;
import web.slack.domain.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;
    private final MemberRepository memberRepository;

    public List<ChatroomResponseDTO> findChatrooms() {
        List<Chatroom> chatrooms = chatroomRepository.findAll();
        List<ChatroomResponseDTO> chatroomResponseDTOS = new ArrayList<>();

        for (Chatroom chatroom : chatrooms) {
            ChatroomResponseDTO chatroomResponseDTO = chatroom.toDTO();
            chatroomResponseDTOS.add(chatroomResponseDTO);
        }
        return chatroomResponseDTOS;
    }

    public ChatroomResponseDTO findChatroom(String chatroomId) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId).orElseThrow(()
                -> new IllegalArgumentException("없는 채널입니다"));
        return chatroom.toDTO();
    }

    @Transactional
    public ChatroomResponseDTO addChatroom(ChatroomRequestDTO chatroomRequestDTO) {
        Chatroom chatroom = chatroomRequestDTO.toEntity();
        if(chatroomRequestDTO.getTeammate()!=null) {
            chatroom.updateTeammate(findMemberById(chatroomRequestDTO.getTeammate()));
        }
        String chatroomId = chatroomRepository.save(chatroom).getId();

        Chatroom savedChatroom = chatroomRepository.findById(chatroomId).orElseThrow(()
                -> new IllegalArgumentException("없는 채널입니다"));

        return savedChatroom.toDTO();
    }

    public String removeChatroom(String chatroomId) {
        try{
            chatroomRepository.deleteById(chatroomId);
            return "success";
        }
        catch(Exception e) {
            return "failed: " +e.getMessage();
        }
    }

    public List<Member> findMemberById(List<String> memberIds) {
        List<Member> members = new ArrayList<>();
        for(String memberId : memberIds) {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다"));
            members.add(member);
        }
        return members;
    }

    public List<String> extractMemberId(List<Member> members) {
        List<String> memberIds = new ArrayList<>();
        for(Member member : members) {
            String memberId = member.getId();
            memberIds.add(memberId);
        }
        return memberIds;
    }

}
