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
    private List<String> profileIdList;


    @Builder
    public WorkspaceResponseDto(Workspace entity){
        this.id = entity.getId();
        this.name = entity.getName();
        if(entity.getProfileIdList() != null)
            this.profileIdList = entity.getProfileIdList();
    }


}
