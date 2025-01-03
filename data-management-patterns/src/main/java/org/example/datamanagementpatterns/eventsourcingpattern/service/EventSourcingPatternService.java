package org.example.datamanagementpatterns.eventsourcingpattern.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventSourcingPatternService {

    private static final String PATTERNS_DIRECTORY = "src/main/resources/eventsourcing"; // Path to your pattern files directory

    public Map<String, String> getPatternFiles(String patternName) throws IOException {
        // A map to hold the content of the files related to the given pattern
        Map<String, String> patternFilesContent = new HashMap<>();

        // Scan the event sourcing directory for files related to the patternName
        Path patternDirectoryPath = Paths.get(PATTERNS_DIRECTORY, patternName);

        if (Files.exists(patternDirectoryPath) && Files.isDirectory(patternDirectoryPath)) {
            // Read the content of the pattern files (e.g., event files, aggregates)
            Files.walk(patternDirectoryPath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            String content = Files.readString(file);  // Read the content of the file
                            patternFilesContent.put(file.getFileName().toString(), content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } else {
            throw new IOException("Pattern not found or invalid pattern directory: " + patternName);
        }

        return patternFilesContent;
    }
}
