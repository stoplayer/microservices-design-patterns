package org.example.securitypatterns.ratelimiting.controller;

import org.example.securitypatterns.ratelimiting.service.RateLimiterPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns/ratelimiting")
public class RateLimiterPatternController {

    @Autowired
    private RateLimiterPatternService rateLimiterPatternService;

    @PostMapping("/get-files")
    public ResponseEntity<Map<String, String>> getPatternFiles(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            Map<String, String> filesContent = rateLimiterPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(filesContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
