
package web.slack.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Profile;

import java.util.List;

@Getter
// @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WorkspaceRequestDto {

    private String id;
    private String name;
    private List<Profile> teammate;


    @Builder
    public WorkspaceRequestDto(String name, String id, List<Profile> teammate){
        this.id = id;
        this.name = name;
        this.teammate = teammate;
    }

}
