package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Workspace;

@Getter
public class WorkspaceResponseDto {

    private String id;
    private String name;

    @Builder
    public WorkspaceResponseDto(Workspace entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }

}
