package org.example.datamanagementpatterns.eventsourcingpattern.repository;

import org.example.datamanagementpatterns.eventsourcingpattern.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.datamanagementpatterns.domain.Order;
import org.example.datamanagementpatterns.domain.OrderDTO;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private Map<Long, Order> orderStore = new ConcurrentHashMap<>();

    // Save an order to the repository
    public void save(Order order) {
        orderStore.put(order.getId(), order);
        logger.info("Order saved: {}", order.getId());
    }

    // Find an order by ID
    public Optional<Order> find(Long orderId) {
        return Optional.ofNullable(orderStore.get(orderId));
    }

    // Optional: Custom find method that throws an exception if the order is not found
    public Order findById(Long orderId) {
        return Optional.ofNullable(orderStore.get(orderId))
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
    }
}
