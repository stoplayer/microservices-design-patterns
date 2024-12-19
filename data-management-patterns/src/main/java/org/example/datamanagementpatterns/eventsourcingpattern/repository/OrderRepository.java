package org.example.datamanagementpatterns.eventsourcingpattern.repository;

import org.example.datamanagementpatterns.eventsourcingpattern.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    private Map<Long, Order> orderStore = new HashMap<>();

    public void save(Order order) {
        orderStore.put(order.getId(), order);
        System.out.println("Order saved: " + order.getId());
    }

    public Order find(Long orderId) {
        return orderStore.get(orderId);
    }
}
