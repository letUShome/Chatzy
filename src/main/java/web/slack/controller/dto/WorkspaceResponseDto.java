package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Profile;
import web.slack.domain.entity.Workspace;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WorkspaceResponseDto {

    private String id;
    private String name;
    private List<ProfileResponseDto> teammates;


    @Builder
    public WorkspaceResponseDto(Workspace entity, List<ProfileResponseDto> profileList){
        this.id = entity.getId();
        this.name = entity.getName();
        this.teammates = profileList;
    }


}
