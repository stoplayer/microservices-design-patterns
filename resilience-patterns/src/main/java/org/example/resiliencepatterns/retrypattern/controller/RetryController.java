package org.example.resiliencepatterns.retrypattern.controller;

import org.example.resiliencepatterns.retrypattern.service.RetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RetryController {
    private final RetryService retryService;

    public RetryController(RetryService retryService) {
        this.retryService = retryService;
    }

    @GetMapping("/test")
    public String testService() {
        return retryService.callExternalService();
    }

}
