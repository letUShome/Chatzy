package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.slack.controller.dto.TestDto;
import web.slack.domain.entity.Test;
import web.slack.service.TestService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private final TestService testService;

    @GetMapping
    public List<Test> testList(){
        return testService.findTests();
    }

}
