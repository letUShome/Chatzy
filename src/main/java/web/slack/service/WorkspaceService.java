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
                .teammate(workspaceRequestDto.getTeammate())
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
    public WorkspaceResponseDto updateWorkspace(String id, Workspace workspace){
        Optional<Workspace> workspaceData = workspaceRepository.findById(id);

        if(workspaceData.isPresent()) {
            Workspace _workspace = workspaceData.get();
            _workspace.setName(workspace.getName());
            _workspace.setTeammate(workspace.getTeammate());
            return new WorkspaceResponseDto(workspaceRepository.save(_workspace));
        }
        else{
            return new WorkspaceResponseDto(workspace);
        }
    }

    // 워크스페이스 삭제
    public void deleteWorkspace (String id){
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 워크스페이스가 없습니다. id = " + id));
        workspaceRepository.delete(workspace);
    }

    // 워크스페이스 내 초대 메일 전송
    public String mailSend(String workspaceId, String email, MailDto mailDto){
        Member entity = memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("유저가 없습니다"));
        try{
            String auth = getAuthCode(6);
            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(email);
            // 보내는 사람
            mailHandler.setFrom(FROM_ADDRESS);
            // 제목
            mailHandler.setSubject(mailDto.getTitle());
            // 내용
            String htmlContent = "<p>" + mailDto.getMessage() +"</p>" +
                    "<p><a href='https://localhost:8080/workspace/" + workspaceId + "/invite?mailKey=" + auth + "'>여기를 클릭하세요</a></p>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();
            entity.updateAuthKey(auth); // DB에 유저의 authkey 저장

            memberInviteRepository.save(entity);

            return "success";
        }
        catch(Exception e){
            e.printStackTrace();
            return "error: " + e.getMessage();
        }
    }

    public String mailConfirm(String email, String auth){
        Member entity = memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("유저가 없습니다"));
        if (entity==null) {
            return "없는 유저입니다.";
        }
        String realAuth = entity.getAuthKey();
        if(realAuth.equals(auth)) {
            entity.updateAuthKey(null);
            return "email : "+email;
        } else {
            return "something went wrong!";
        }


    }

    private String getAuthCode(int size) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;
        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }
        return buffer.toString();
    }

}
