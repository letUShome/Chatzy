package web.slack.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatroomType {
    DM("TYPE_DM", "DM"),
    GROUP("TYPE_GROUP", "GROUP");

    private final String key;
    private final String value;
}
