package org.example.datamanagementpatterns.eventsourcingpattern.repository;

import org.example.datamanagementpatterns.eventsourcingpattern.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository<Event> extends JpaRepository<Event, Long> {
    // Here, you could define custom queries if needed in the future
}
