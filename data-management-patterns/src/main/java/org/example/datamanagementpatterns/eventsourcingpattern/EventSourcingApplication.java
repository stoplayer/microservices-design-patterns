package org.example.datamanagementpatterns.eventsourcingpattern;

import org.example.datamanagementpatterns.eventsourcingpattern.event.OrderCreatedEvent;
import org.example.datamanagementpatterns.eventsourcingpattern.event.OrderConfirmedEvent;
import org.example.datamanagementpatterns.eventsourcingpattern.event.OrderEventHandler;
import org.example.datamanagementpatterns.eventsourcingpattern.event.OrderEventStore;
import org.example.datamanagementpatterns.eventsourcingpattern.common.Event;

@SpringBootTest
public class EventSourcingApplication {

    public static void main(String[] args) {
        // Initialize the event store and event handler
        OrderEventStore eventStore = new OrderEventStore();
        OrderEventHandler eventHandler = new OrderEventHandler(eventStore);

        // Create some events
        Event orderCreatedEvent = new OrderCreatedEvent(1L, "Order created successfully");
        Event orderConfirmedEvent = new OrderConfirmedEvent(1L, "Order confirmed");

        // Store the events in the event store
        eventStore.storeEvent(orderCreatedEvent);
        eventStore.storeEvent(orderConfirmedEvent);

        // Handle the events (in this case, just print them out)
        eventHandler.handleEvent(orderCreatedEvent);
        eventHandler.handleEvent(orderConfirmedEvent);

    }
}
