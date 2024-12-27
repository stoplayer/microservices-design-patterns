package org.example.resiliencepatterns.circuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {

    @CircuitBreaker(name = "serviceA", fallbackMethod = "fallbackMethod")
    public String someOperation() {
        // Your service call that might fail
        return externalServiceCall();
    }

    public String fallbackMethod(Exception ex) {
        // Fallback logic when circuit is open
        return "Fallback response";
    }

    private String externalServiceCall() {
        // Simulate external service call
        // This could be a REST call or any other operation
        if (Math.random() < 0.4) { // Simulate 40% failure rate
            throw new RuntimeException("Service call failed");
        }
        return "Success";
    }
}
