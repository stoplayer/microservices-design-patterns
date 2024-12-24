package org.example.securitypatterns.Jwtauth.controller;

import org.example.securitypatterns.Jwtauth.service.JwtPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns")
public class JwtPatternController {

    @Autowired
    private JwtPatternService jwtPatternService;

    @PostMapping("/get-pattern-code")
    public ResponseEntity<Map<String, String>> getPatternCode(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            Map<String, String> patternFiles = jwtPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(patternFiles);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
