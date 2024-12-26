package org.example.datamanagementpatterns.eventsourcingpattern.controller;

import org.example.datamanagementpatterns.eventsourcingpattern.service.EventSourcingPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-sourcing")
public class EventSourcingPatternController {

    @Autowired
    private EventSourcingPatternService eventSourcingPatternService;

    // Endpoint to handle code modification based on user input and pattern selection
    @PostMapping("/apply")
    public String applyEventSourcingPattern(@RequestBody String userCode) {
        return eventSourcingPatternService.modifyCodeWithPattern(userCode);
    }
}
