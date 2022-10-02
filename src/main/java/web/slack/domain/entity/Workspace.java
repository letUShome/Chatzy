package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
public class Workspace {

    @Id
    private String id;

    private String name;

    @Builder
    public Workspace (String id, String name){
        this.id = id;
        this.name = name;
    }

}
