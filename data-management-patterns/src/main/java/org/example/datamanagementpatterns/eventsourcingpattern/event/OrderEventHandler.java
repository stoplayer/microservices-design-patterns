package org.example.datamanagementpatterns.eventsourcingpattern.event;

public class OrderEventHandler {
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        // Event handling logic for order creation
        System.out.println("Order created: " + event.getOrderId());
    }
    public void handleEvent(Event event) {
        // handle the event logic here
    }

    // Other event handling methods can be added here, such as order updates
}
