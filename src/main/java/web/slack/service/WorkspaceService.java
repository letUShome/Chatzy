package web.slack.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.EmailRequestDto;
import web.slack.controller.dto.WorkspaceRequestDto;
import web.slack.controller.dto.WorkspaceResponseDto;
import web.slack.domain.entity.Workspace;
import web.slack.domain.repository.WorkspaceRepository;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "efubslack@gmail.com";
    ObjectMapper objectMapper = new ObjectMapper();

    public List<WorkspaceResponseDto> findWorkspaceList(){
        List<Workspace> workspaceList = workspaceRepository.findAll();
        List<WorkspaceResponseDto> workspaceResponseDtoList = new ArrayList<>();

        for (Workspace workspace : workspaceList){
            WorkspaceResponseDto workspaceResponseDto = workspace.toDTO();
            workspaceResponseDtoList.add(workspaceResponseDto);
        }
        return workspaceResponseDtoList;
    }

    // 워크스페이스 상세 조회
    public WorkspaceResponseDto findWorkspace(String id){
        Workspace entity = workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 워크스페이스가 없습니다. id=" + id));
        return entity.toDTO();
    }

    @Transactional
    public WorkspaceResponseDto addWorkspace(WorkspaceRequestDto workspaceRequestDto){
        Workspace workspace = workspaceRequestDto.toEntity();
        workspaceRepository.save(workspace);

        return workspace.toDTO();
    }

    @Transactional
    public Workspace applyPatchToWorkspace(JsonPatch patch, String workspace_id) throws JsonPatchException, JsonProcessingException {
        Workspace workspace = workspaceRepository.findById(workspace_id).orElseThrow(() -> new IllegalArgumentException("워크스페이스가 존재하지 않습니다."));
        JsonNode patched = patch.apply(objectMapper.convertValue(workspace, JsonNode.class));

        return objectMapper.treeToValue(patched, Workspace.class);
    }

    @Transactional
    public WorkspaceResponseDto modifyWorkspace(Workspace workspacePatched){
        Workspace workspace = workspaceRepository.save(workspacePatched);

        return workspace.toDTO();
    }

    // 워크스페이스 삭제
    @Transactional
    public void deleteWorkspace (String id){
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 워크스페이스가 없습니다. id = " + id));
        workspaceRepository.delete(workspace);
    }

    public void addTeammate(EmailRequestDto emailRequestDto) {

    }
}

