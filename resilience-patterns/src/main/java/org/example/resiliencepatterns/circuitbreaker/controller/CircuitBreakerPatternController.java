package org.example.resiliencepatterns.circuitbreaker.controller;

import org.example.resiliencepatterns.circuitbreaker.service.CircuitBreakerPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns/circuit-breaker")
public class CircuitBreakerPatternController {

    @Autowired
    private CircuitBreakerPatternService circuitBreakerPatternService;

    @PostMapping("/get-files")
    public ResponseEntity<Map<String, String>> getPatternFiles(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            Map<String, String> filesContent = circuitBreakerPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(filesContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
