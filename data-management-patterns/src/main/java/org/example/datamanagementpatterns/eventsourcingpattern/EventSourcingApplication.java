package org.example.datamanagementpatterns.eventsourcingpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example.datamanagementpatterns.eventsourcingpattern") // Ensure the correct base package
public class EventSourcingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingApplication.class, args);
    }
}
