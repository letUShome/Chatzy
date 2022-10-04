
package web.slack.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
// @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WorkspaceRequestDto {

    private String id;
    private String name;

    @Builder
    public WorkspaceRequestDto(String name, String id){
        this.id = id;
        this.name = name;
    }

}
