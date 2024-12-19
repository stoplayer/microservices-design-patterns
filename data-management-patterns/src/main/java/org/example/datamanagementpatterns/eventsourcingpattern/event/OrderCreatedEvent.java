package org.example.datamanagementpatterns.eventsourcingpattern.event;

import org.example.datamanagementpatterns.eventsourcingpattern.common.Event;

public class OrderCreatedEvent implements Event {
    private Long orderId;
    private String message;

    public OrderCreatedEvent(Long orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }
}
