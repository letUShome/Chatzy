package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.TestDto;
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
}
