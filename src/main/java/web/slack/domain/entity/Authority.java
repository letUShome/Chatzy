package web.slack.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {
    ADMIN("ROLE_ADMIN", "admin"),
    TEAMMATE("ROLE_TEAMMATE", "teammate");

    private final String key;
    private final String title;
}
