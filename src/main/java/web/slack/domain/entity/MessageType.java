package web.slack.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    ALARM("TYPE_ALARM", "alarm"),
    TALK("TYPE_TALK", "talk");

    private final String key;
    private final String title;
}
