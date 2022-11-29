package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.slack.controller.dto.TestDto;
import web.slack.controller.dto.TestRequestDto;
import web.slack.domain.entity.Test;
import web.slack.service.TestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping()
public class TestController {

    private final TestService testService;

    @GetMapping()
    public String buildtest(){
        return "정상적으로 배포되었습니다: 2022. 11. 29 20:20";
    }

    @GetMapping
    public List<Test> testList(){
        return testService.findTests();
    }

    @PostMapping("/api/v1/test")
    public String addTest(@RequestBody TestRequestDto requestDto){
        return testService.saveTest(requestDto);
    }

}
