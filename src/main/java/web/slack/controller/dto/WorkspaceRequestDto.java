
package web.slack.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Profile;
import web.slack.domain.entity.Workspace;

import java.util.List;

@Getter
// @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WorkspaceRequestDto {

    private String id;
    private String name;
    private List<String> profileIdList;


    @Builder
    public WorkspaceRequestDto(String name, String id, List<String> profileIdList){
        this.id = id;
        this.name = name;
        this.profileIdList = profileIdList;
    }

    public Workspace toEntity(){
        return Workspace.builder()
                .name(this.getName())
                .profileIdList(this.getProfileIdList())
                .build();

    }

    public void updateWorkspaceDTO(List<String> profileIdList){
        this.profileIdList = profileIdList;
    }


}
