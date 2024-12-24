package org.example.securitypatterns.Jwtauth.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtPatternService {

    private static final String PATTERN_BASE_FOLDER = "D:\\My data file\\studies\\5 MG\\S1\\Architecture des composants d'entreprise\\Patron de conception microservices\\Code\\Backend\\microservices-design-patterns\\security-patterns\\src\\main\\java\\org\\example\\securitypatterns\\";

    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        Map<String, String> filesContent = new HashMap<>();

        // Base folder for the design pattern
        Path patternFolder = Paths.get(PATTERN_BASE_FOLDER, patternName);

        // List of files to read
        String[] fileNames = {
                "config/CorsConfig.java",
                "config/JWTAuthFilter.java",
                "config/SecurityConfig.java",
                "controller/AuthController.java",
                "dto/ReqRes.java",
                "entity/OurUsers.java",
                "repository/OurUserRepo.java",
                "service/AuthService.java",
                "service/JWTUtils.java",
                "service/OurUserDetailsService.java"
        };

        for (String fileName : fileNames) {
            // Construct the full path for the file
            Path filePath = patternFolder.resolve(fileName);
            System.out.println("Trying to load file: " + filePath.toAbsolutePath());
            System.out.println("Current Working Directory: " + Paths.get("").toAbsolutePath());

            // Check if the file exists and read its content
            if (Files.exists(filePath)) {
                String content = Files.readString(filePath, StandardCharsets.UTF_8);
                filesContent.put(fileName, content);
            } else {
                throw new IOException("File not found: " + filePath.toAbsolutePath());
            }
        }

        return filesContent;
    }
}
