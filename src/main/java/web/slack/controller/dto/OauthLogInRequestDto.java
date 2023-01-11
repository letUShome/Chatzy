package web.slack.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OauthLogInRequestDto {

    String id;
    String code;

}
