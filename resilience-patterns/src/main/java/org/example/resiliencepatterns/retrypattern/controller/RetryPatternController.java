package org.example.resiliencepatterns.retrypattern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns/retry")
public class RetryPatternController {

    @Autowired
    private RetryPatternService retryPatternService;

    @PostMapping("/get-files")
    public ResponseEntity<Map<String, String>> getPatternFiles(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            Map<String, String> filesContent = retryPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(filesContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
;;