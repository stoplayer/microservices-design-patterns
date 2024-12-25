package org.example.patterns.saga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.example.patterns.saga.service.SagaPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns/saga")
public class SagaPatternController {

    @Autowired
    private SagaPatternService sagaPatternService;

    @PostMapping("/get-files")
    public ResponseEntity<Map<String, String>> getPatternFiles(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            // Call the service to get the SAGA pattern files based on patternName
            Map<String, String> filesContent = sagaPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(filesContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
