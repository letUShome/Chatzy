package web.slack.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    private String address;
    private String workspaceId;

/*    @Builder
    public MailDto(String address, String workspaceId){
        this.address = address;
        this.workspaceId = workspaceId;
    }*/
}