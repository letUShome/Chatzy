package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "Profile")
public class Profile {

    @Id
    private String id;

    private String name;

    private String nickname;

    private String role;

    @Builder
    public Profile(String id, String name, String nickname, String role){
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setRole(String role){ this.role = role; }

}
