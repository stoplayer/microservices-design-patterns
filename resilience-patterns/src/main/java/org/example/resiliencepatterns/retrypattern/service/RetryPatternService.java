package org.example.resiliencepatterns.retrypattern.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class RetryPatternService {

    @Retryable(value = {IOException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        // Simulate a transient failure
        if (Math.random() > 0.7) {
            throw new IOException("Temporary failure - Please try again.");
        }

        // Mock pattern file content
        return Map.of("RetryPattern.java", "public class RetryPattern { /* Implementation */ }");
    }

    @Recover
    public Map<String, String> recover(IOException e, String patternName) {
        return Map.of("error", "Failed to fetch pattern files after multiple attempts: " + e.getMessage());
    }
}
