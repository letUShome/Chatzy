package web.slack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "Test")
public class Test {
    @Id
    private String id;

    private String name;

    private String contents;

    @Builder
    public Test(String id, String name, String contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }
}
