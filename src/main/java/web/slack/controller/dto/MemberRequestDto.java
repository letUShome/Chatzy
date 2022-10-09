package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    String name;
    String email;
    String password;

    /**
     * Sign-up Request*
     * @param name
     * @param email
     * @param password
     */
    @Builder
    public MemberRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Log-in Request*
     * @param email
     * @param password
     */
    @Builder
    public MemberRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
