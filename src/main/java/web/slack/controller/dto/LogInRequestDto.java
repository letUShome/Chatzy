package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogInRequestDto {
    String email;
    String password;

    @Builder
    public LogInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
