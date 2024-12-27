package org.example.communicationpatterns.gateway.controller;

import org.example.communicationpatterns.gateway.service.ApiGatewayPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/design-patterns/apigateway")
public class ApiGatewayPatternController {

    @Autowired
    private ApiGatewayPatternService apiGatewayPatternService;

    @PostMapping("/get-files")
    public ResponseEntity<Map<String, String>> getPatternFiles(@RequestBody Map<String, String> request) {
        try {
            String patternName = request.get("patternName");
            if (patternName == null || patternName.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Pattern name is required"));
            }

            Map<String, String> filesContent = apiGatewayPatternService.getPatternFiles(patternName);
            return ResponseEntity.ok(filesContent);
        } catch (IOException e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Failed to retrieve pattern files: " + e.getMessage()));
        }
    }
}
