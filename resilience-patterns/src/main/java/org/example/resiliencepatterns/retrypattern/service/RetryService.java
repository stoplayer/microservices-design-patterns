package org.example.resiliencepatterns.retrypattern.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RetryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryService.class);
    private final RestTemplate restTemplate;
    private int attemptCount = 1;

    public RetryService() {
        this.restTemplate = new RestTemplate();
    }

    @Retry(name = "serviceA", fallbackMethod = "fallback")
    public String callExternalService() {

        // Simulate an external service call that might fail
        if (attemptCount++ <= 2) { // First two attempts will fail
            throw new RuntimeException("External service failed");
        }
        // Reset counter for next calls
        attemptCount = 1;
        return "External service response success";
    }

    public String fallback(Exception ex) {

        // Reset counter for next calls
        attemptCount = 1;
        return "Fallback response after all retries failed";
    }
}