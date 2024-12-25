package org.example.patterns.eventsourcing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.example.patterns.eventsourcing.service.EventSourcingPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns/eventsourcing")
public class EventSourcingPatternController {

    @Autowired
    private EventSourcingPatternService eventSourcingPatternService;

    @PostMapping("/get-files")
    public ResponseEntity<Map<String, String>> getPatternFiles(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            // Call the service to get the event sourcing pattern files based on patternName
            Map<String, String> filesContent = eventSourcingPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(filesContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
