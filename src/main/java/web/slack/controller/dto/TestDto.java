package web.slack.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
public class TestDto {
    @Id
    private String id;

    private String name;

    private String contents;

    @Builder
    public TestDto(String id, String name, String contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }
}
