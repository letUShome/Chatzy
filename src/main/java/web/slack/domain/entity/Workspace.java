package web.slack.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "Workspace")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Workspace {

    @Id
    private String id;

    private String name;

    @Builder
    public Workspace (String name, String id){
        this.id = id;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

}
