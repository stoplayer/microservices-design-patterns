package org.example.resiliencepatterns.circuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CircuitBreakerPatternService {

    @CircuitBreaker(name = "defaultCircuitBreaker", fallbackMethod = "fallbackMethod")
    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        // Simulate a service call failure
        if (Math.random() > 0.5) {
            throw new IOException("Service temporarily unavailable.");
        }

        // Mock pattern file content
        return Map.of("CircuitBreakerPattern.java", "public class CircuitBreakerPattern { /* Implementation */ }");
    }

    public Map<String, String> fallbackMethod(String patternName, Throwable throwable) {
        return Map.of("error", "Service is currently unavailable: " + throwable.getMessage());
    }
}
