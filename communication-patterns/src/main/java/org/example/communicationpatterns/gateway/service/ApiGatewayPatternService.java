package org.example.communicationpatterns.gateway.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiGatewayPatternService {

    private static final String PATTERN_BASE_FOLDER = "\\Users\\mac\\Desktop\\microservices-design-patterns\\communication-patterns\\src\\main\\java\\org\\example\\communicationpatterns\\";

    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        Map<String, String> filesContent = new HashMap<>();

        String[] fileNames = {
                "config/GatewayConfig.java",
                "controller/ApiGatewayController.java",
                "service/ApiGatewayService.java",
                "filter/CustomFilter.java"
        };

        for (String fileName : fileNames) {
            String filePath = PATTERN_BASE_FOLDER + fileName;
            System.out.println("Checking file path: " + filePath);

            if (!Files.exists(Paths.get(filePath))) {
                System.err.println("File not found: " + filePath);
                continue; // Skip this file if it doesn't exist
            }

            try {
                String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
                filesContent.put(fileName, content);
            } catch (IOException e) {
                System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
            }
        }

        return filesContent;
    }


    private String loadFileContent(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
    }
}
