package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "Member")
public class Member {
    @Id
    private String id;

    private String name;

    private String email;

    private String profile;

    private Role role;

    @Builder
    public Member(String id, String name, String email, String profile, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.role = role;
    }
}
