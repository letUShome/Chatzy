package web.slack.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
