package web.slack.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChannelType {
    DM("TYPE_DM", "dm"),
    GROUP("TYPE_GROUP", "group");

    private final String key;
    private final String value;
}
