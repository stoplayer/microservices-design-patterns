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

    private static final String PATTERN_BASE_FOLDER = "D:\\My data file\\studies\\5 MG\\S1\\Architecture des composants d'entreprise\\Patron de conception microservices\\Code\\Backend\\microservices-design-patterns\\communication-patterns\\src\\main\\java\\org\\example\\communicationpatterns\\";

    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        Map<String, String> filesContent = new HashMap<>();

        // Correct file paths by including the patternName in the base folder
        String[] fileNames = {
                "config/GatewayConfig.java",
                "controller/ApiGatewayController.java",
                "service/ApiGatewayService.java",
                "filter/CustomFilter.java"
        };

        for (String fileName : fileNames) {
            // Construct the file path by including the patternName directory
            String filePath = PATTERN_BASE_FOLDER + patternName + "/" + fileName;
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
}
