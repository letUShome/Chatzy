package web.slack.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TestRequestDto {

    @JsonProperty("sub_id")
    private String subId;

    private String name;

    private String contents;

    @Builder
    public TestRequestDto(String subId, String name, String contents) {
        this.subId = subId;
        this.name = name;
        this.contents = contents;
    }
}
