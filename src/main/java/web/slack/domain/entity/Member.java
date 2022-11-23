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

    private String password;

    private String image;

    private Role role;

    private Boolean verified;

    @Builder
    public Member(String id, String name, String email, String image, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.verified = false;
    }


    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
