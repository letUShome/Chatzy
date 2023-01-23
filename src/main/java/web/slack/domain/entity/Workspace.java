package web.slack.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import web.slack.controller.dto.WorkspaceResponseDto;

import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "Workspace")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Workspace {

    @Id
    private String id;

    private String name;

    private String image;

    private List<String> teammate;

    @Builder
    public Workspace (String id, String name, String image, List<String> profileIdList){
        this.id = id;
        this.image = image;
        this.name = name;
        this.teammate = profileIdList;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public void updateTeammate(List<String> profileIdList) {
        this.teammate = profileIdList;
    }

    public WorkspaceResponseDto toDTO() {
        return WorkspaceResponseDto.builder()
                .entity(this)
                .build();
    }


}
