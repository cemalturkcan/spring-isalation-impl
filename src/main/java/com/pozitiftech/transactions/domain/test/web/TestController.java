package com.pozitiftech.transactions.domain.test.web;

import com.pozitiftech.transactions.domain.test.api.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final TestService testService;

    @GetMapping("/read-committed")
    public Double testSetBalanceReadCommitted() throws InterruptedException {
        return testService.testSetBalanceReadCommitted();
    }

    @GetMapping("/repeatable-read")
    public Double testSetBalanceRepeatableRead() throws InterruptedException {
        return testService.testSetBalanceRepeatableRead();
    }

}
