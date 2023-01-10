package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(collection = "GoogleCode")
public class GoogleCode {

    @Id
    private String id;

    private Member member;

    private String code;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public GoogleCode(String id, Member member, String code, LocalDateTime createdAt) {
        this.id = id;
        this.member = member;
        this.code = code;
        this.createdAt = createdAt;
    }
}
