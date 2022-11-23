package web.slack.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.MailDto;
import web.slack.controller.dto.WorkspaceRequestDto;
import web.slack.controller.dto.WorkspaceResponseDto;
import web.slack.domain.entity.Workspace;
import web.slack.service.CustomOauth2UserService;
import web.slack.service.WorkspaceService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspaces")
@Slf4j
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

    @PostMapping("/{id}")
    public WorkspaceResponseDto updateWorkspace(@PathVariable String id, @RequestBody WorkspaceRequestDto workspaceRequestDto){
        return workspaceService.updateWorkspace(id, workspaceRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteWorkspace(@PathVariable String id){
        workspaceService.deleteWorkspace(id);
        return id;
    }


//    @PostMapping("/{id}/invite")
//    public String sendMail(@PathVariable String id,@RequestBody String email){
//        log.info("이메일: " + email);
//
//        System.out.println(email);
//        String title = "[워크스페이스 초대]";
//        String msg = "안녕하세요. 해당 워크스페이스 링크로 접속하여 워크스페이스에 가입해주세요";
//        MailDto mailDto = new MailDto(email, title, msg);
//        return workspaceService.mailSend(id, email, mailDto);
//
//    }
//
//    @GetMapping("/{id}/enter")
//    public String confirmMail(@PathVariable String email, @RequestParam String mailKey) {
//        return workspaceService.mailConfirm(email, mailKey);
//    }
//
}
