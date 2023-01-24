package web.slack.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.*;
import web.slack.domain.entity.Workspace;
import web.slack.service.EmailService;
import web.slack.service.ProfileService;
import web.slack.service.WorkspaceService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspaces")
@Slf4j
public class WorkspaceController {

    private final WorkspaceService workspaceService;
    private final ProfileService profileService;
    private final EmailService emailService;

    @PostMapping()
    public WorkspaceResponseDto workspaceAdd(@RequestBody WorkspaceRequestDto workspaceRequestDto){
        return workspaceService.addWorkspace(workspaceRequestDto);
    }

    @PostMapping("/{workspace_id}/invitation")
    public String workspaceModify(@PathVariable String workspaceId, @RequestBody EmailRequestDto emailRequestDto){
        List<String> emailList = emailRequestDto.getEmail();
        for (String s : emailList) {
            ProfileRequestDto profileRequestDto = ProfileRequestDto
                    .builder()
                    .workspaceId(workspaceId)
                    .nickname(s).build();

            ProfileResponseDto profileResponseDto = profileService.saveProfile(profileRequestDto);
            workspaceService.addTeammate(profileResponseDto, workspaceId, emailRequestDto); //초대이메일을 보니까 아직 메일을 수락하지 않았음에도 이미 list에 넣어놓음
            emailService.sendEmail(emailRequestDto);
        }
        return "ok";
    }

    @GetMapping()
    public List<WorkspaceResponseDto> workspaceList(){
        return workspaceService.findWorkspaceList();
    }

    @GetMapping("/{workspace_id}")
    public WorkspaceResponseDto workspaceDetails (@PathVariable String workspaceId){
        List<ProfileResponseDto> profileList = profileService.findProfileListByWorkspace(workspaceId);
        return workspaceService.findWorkspace(workspaceId, profileList);
    }

    @PatchMapping(path = "/{workspace_id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> workspaceModify(@PathVariable String workspaceId, @RequestBody JsonPatch patch){
        try {
            Workspace workspacePatched = workspaceService.applyPatchToWorkspace(patch, workspaceId);
            WorkspaceResponseDto workspaceDTO = workspaceService.modifyWorkspace(workspacePatched);
            return ResponseEntity.ok(workspaceDTO);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteWorkspace(@PathVariable String id){
        workspaceService.deleteWorkspace(id);
        return "워크스페이스가 삭제되었습니다. id = "+id;
    }
}
