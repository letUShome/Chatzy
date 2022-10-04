package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.WorkspaceRequestDto;
import web.slack.controller.dto.WorkspaceResponseDto;
import web.slack.service.WorkspaceService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping()
    public WorkspaceResponseDto saveWorkspace(@RequestBody WorkspaceRequestDto workspaceRequestDto){
        return workspaceService.saveWorkspace(workspaceRequestDto);
    }

    @GetMapping()
    public List<WorkspaceResponseDto> findAllWorkspaceList(){
        return workspaceService.findAllWorkspaceList();
    }

    @GetMapping("/{id}")
    public WorkspaceResponseDto findById (@PathVariable String id){
        return workspaceService.findById(id);
    }

    @DeleteMapping("{id}")
    public String deleteWorkspace(@PathVariable String id){
        workspaceService.deleteWorkspace(id);
        return id;
    }


}
