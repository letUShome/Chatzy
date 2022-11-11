package web.slack.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailDto {
    private String email;
    private String title;
    private String message;

    public MailDto(String email, String title, String message){
        this.email = email;
        this.title = title;
        this.message = message;
    }

}
