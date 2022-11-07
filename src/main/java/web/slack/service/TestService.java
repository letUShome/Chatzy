package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.slack.controller.dto.TestDto;
import web.slack.controller.dto.TestRequestDto;
import web.slack.domain.entity.Test;
import web.slack.domain.repository.TestRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;
    public List<Test> findTests() {
        List<Test> testList = testRepository.findAll();
        return testList;
    }

    @Transactional
    public String saveTest(TestRequestDto requestDto) {
        Test test = Test.builder()
                .contents(requestDto.getContents())
                .subId(requestDto.getSubId())
                .name(requestDto.getName())
                .contents(requestDto.getContents())
                .build();
        testRepository.save(test);
        return test.getSubId();
    }
}
