package web.slack.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.MailDto;
import web.slack.controller.dto.OauthAttributes;
import web.slack.controller.dto.WorkspaceRequestDto;
import web.slack.controller.dto.WorkspaceResponseDto;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Workspace;
import web.slack.domain.repository.MemberInviteRepository;
import web.slack.domain.repository.WorkspaceRepository;
import web.slack.domain.repository.MemberRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "efubslack@gmail.com";


    public WorkspaceService(WorkspaceRepository workspaceRepository, MemberRepository memberRepository, MemberInviteRepository memberInviteRepository) {
        this.workspaceRepository = workspaceRepository;
        this.memberRepository = memberRepository;
        this.memberInviteRepository = memberInviteRepository;
    }

    public WorkspaceResponseDto buildResponseDto(Workspace entity) {
        return new WorkspaceResponseDto(entity);
    }

    private final MemberRepository memberRepository;

    private final MemberInviteRepository memberInviteRepository;



    // 워크스페이스 생성
    @Transactional
    public WorkspaceResponseDto saveWorkspace(WorkspaceRequestDto workspaceRequestDto){
        Workspace workspace = Workspace.builder()
                .name(workspaceRequestDto.getName())
                .profileIdList(workspaceRequestDto.getProfileIdList())
                .build();

        workspaceRepository.save(workspace);

        return buildResponseDto(workspace);


    }

    // 워크스페이스 전체 조회
    @Transactional
    public List<WorkspaceResponseDto> findAllWorkspaceList(){
        List<Workspace> workspaceList = workspaceRepository.findAll();
        List<WorkspaceResponseDto> workspaceResponseDtoList = new ArrayList<>();
        for (Workspace workspace : workspaceList){
            WorkspaceResponseDto workspaceResponseDto = new WorkspaceResponseDto(workspace);
            workspaceResponseDtoList.add(workspaceResponseDto);
        }
        return workspaceResponseDtoList;
    }

    // 워크스페이스 상세 조회
    @Transactional
    public WorkspaceResponseDto findById(String id){
        Workspace entity = workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 워크스페이스가 없습니다. id " + id));
        return new WorkspaceResponseDto(entity);
    }

    // 워크스페이스 수정
    @Transactional
    public WorkspaceResponseDto updateWorkspace(String id, WorkspaceRequestDto workspaceRequestDto){
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 워크스페이스입니다"));

        workspace.setName(workspaceRequestDto.getName());
        workspace.setProfileIdList(workspaceRequestDto.getProfileIdList());
        return new WorkspaceResponseDto(workspaceRepository.save(workspace));

    }

    // 워크스페이스 삭제
    public void deleteWorkspace (String id){
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 워크스페이스가 없습니다. id = " + id));
        workspaceRepository.delete(workspace);
    }

}

