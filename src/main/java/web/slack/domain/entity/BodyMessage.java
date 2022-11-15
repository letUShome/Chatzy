package web.slack.domain.entity;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class BodyMessage {
    private StatusEnum status;
    private String message;
    private Object data;

    public BodyMessage() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
