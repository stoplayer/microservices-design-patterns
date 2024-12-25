package org.example.datamanagementpatterns.eventsourcingpattern.domain;

import org.example.datamanagementpatterns.eventsourcingpattern.event.OrderCreatedEvent;
import org.example.datamanagementpatterns.eventsourcingpattern.event.OrderConfirmedEvent;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    private Long id;
    private String status;
    private List<Event> events = new ArrayList<>();

    public Order(Long id) {
        this.id = id;
        this.status = "Pending";
    }

    public void createOrder() {
        OrderCreatedEvent event = new OrderCreatedEvent(id, "Order Created");
        applyEvent(event);
    }

    public void confirmOrder() {
        OrderConfirmedEvent event = new OrderConfirmedEvent(id, "Order Confirmed");
        applyEvent(event);
    }

    public void applyEvent(Event event) {
        if (event instanceof OrderCreatedEvent) {
            this.status = "Created";
        } else if (event instanceof OrderConfirmedEvent) {
            this.status = "Confirmed";
        }
        events.add(event);
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<Event> getEvents() {
        return events;
    }
}
