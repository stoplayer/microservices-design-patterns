package org.example.datamanagementpatterns.eventsourcingpattern.service;

import org.example.datamanagementpatterns.eventsourcingpattern.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventSourcingPatternService {

    private final EventRepository eventRepository;

    public EventSourcingPatternService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public String modifyCodeWithPattern(String userCode) {
        return userCode;
    }
}
