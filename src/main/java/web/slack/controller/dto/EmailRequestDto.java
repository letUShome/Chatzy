package web.slack.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class EmailRequestDto {
    private List<String> email;

/*    @Builder
    public MailDto(String address, String workspaceId){
        this.address = address;
        this.workspaceId = workspaceId;
    }*/
}