package web.slack.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "Workspace")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Workspace {

    @Id
    private String id;

    private String name;

    private List<Profile> teammate;

    @Builder
    public Workspace (String id, String name, List<Profile> teammate){
        this.id = id;
        this.name = name;
        this.teammate = teammate;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTeammate(List<Profile> profileIds){
        this.teammate = profileIds;
    }

}
