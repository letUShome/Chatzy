package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.ChatroomRequestDTO;
import web.slack.controller.dto.ChatroomResponseDTO;
import web.slack.domain.entity.Chatroom;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Profile;
import web.slack.domain.repository.ChatroomRepository;
import web.slack.domain.repository.MemberRepository;
import web.slack.domain.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

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
            chatroom.updateTeammate(findTeammateById(chatroomRequestDTO.getTeammate()));
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

    public List<Profile> findTeammateById(List<String> profileIds) {
        List<Profile> teammate = new ArrayList<>();
        for(String profileId : profileIds) {
            Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다"));
            teammate.add(profile);
        }
        return teammate;
    }

    public List<String> extractProfileId(List<Profile> teammate) {
        List<String> profileIds = new ArrayList<>();
        for(Profile profile : teammate) {
            String profileId = profile.getId();
            profileIds.add(profileId);
        }
        return profileIds;
    }

}
