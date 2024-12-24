package org.example.securitypatterns.ratelimiting.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class RateLimiterPatternService {

    private static final String PATTERN_BASE_FOLDER = "D:\\My data file\\studies\\5 MG\\S1\\Architecture des composants d'entreprise\\Patron de conception microservices\\Code\\Backend\\microservices-design-patterns\\security-patterns\\src\\main\\java\\org\\example\\securitypatterns\\";

    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        Map<String, String> filesContent = new HashMap<>();

        // Path to the specific pattern folder
        String patternFolder = PATTERN_BASE_FOLDER + patternName + "\\";
        String[] fileNames = {
                "config\\RedisConfig.java",
                "controller\\RateLimiterController.java",
                "service\\RateLimiterService.java"
        };

        for (String fileName : fileNames) {
            String filePath = patternFolder + fileName;
            // Load the file content
            String content = loadFileContent(filePath);

            if (content == null) {
                throw new IOException("Failed to load file: " + filePath);
            }

            filesContent.put(fileName, content);
        }

        return filesContent;
    }

    private String loadFileContent(String filePath) throws IOException {
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("Failed to load file at: " + filePath, e);
        }
    }
}
