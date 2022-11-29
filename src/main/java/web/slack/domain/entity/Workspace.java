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

    private List<String> profileIdList;

    @Builder
    public Workspace (String id, String name, List<String> profileIdList){
        this.id = id;
        this.name = name;
        if(profileIdList != null){
            this.profileIdList = profileIdList;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProfileIdList(List<String> profileIdList){
        this.profileIdList = profileIdList;
    }


}
