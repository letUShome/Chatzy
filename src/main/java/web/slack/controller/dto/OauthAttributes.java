package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import web.slack.domain.entity.Member;
import web.slack.domain.entity.Role;

import java.util.Map;

@Getter
public class OauthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profile;

    @Builder
    public OauthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profile) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public static OauthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OauthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .email(email)
                .profile(profile)
                .role(Role.USER)
                .build();
    }
}
