package org.example.securitypatterns.ratelimiting.controller;

import org.example.securitypatterns.ratelimiting.service.RateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    @Autowired
    private RateLimiterService rateLimiterService;

    @GetMapping("/api/protected-resource")
    public ResponseEntity<String> accessProtectedResource(@RequestHeader("Client-Id") String clientId) {
        if (!rateLimiterService.isAllowed(clientId)) {
            return ResponseEntity.status(429).body("Too Many Requests - Please try again later.");
        }

        return ResponseEntity.ok("Access granted to protected resource!");
    }
}
