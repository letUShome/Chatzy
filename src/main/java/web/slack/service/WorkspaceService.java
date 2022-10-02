package web.slack.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.WorkspaceRequestDto;
import web.slack.controller.dto.WorkspaceResponseDto;
import web.slack.domain.entity.Workspace;
import web.slack.domain.repository.WorkspaceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public WorkspaceResponseDto buildResponseDto(Workspace entity) {
        return new WorkspaceResponseDto(entity);
    }

    // 워크스페이스 생성
    @Transactional
    public WorkspaceResponseDto saveWorkspace(WorkspaceRequestDto workspaceRequestDto){
        Workspace workspace = Workspace.builder()
                .name(workspaceRequestDto.getName())
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

    // 워크스페이스 삭제
    public void deleteWorkspace (String id){
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 워크스페이스가 없습니다. id = " + id));
        workspaceRepository.delete(workspace);
    }

}
