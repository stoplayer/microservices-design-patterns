package org.example.datamanagementpatterns.repository;

import org.example.patterns.saga.domain.Order;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    // In-memory store to simulate a database
    private Map<Long, Order> orderStore = new HashMap<>();

    // Method to save a new or updated order
    public void save(Order order) {
        // Logic to persist the order in a database (simulated here with an in-memory store)
        orderStore.put(order.getId(), order);
        System.out.println("Order saved: " + order.getId());
    }

    // Method to retrieve an order by ID
    public Order findOne(Long orderId) {
        // Logic to find and return an order by its ID
        Order order = orderStore.get(orderId);
        if (order == null) {
            System.out.println("Order not found with ID: " + orderId);
        } else {
            System.out.println("Order found: " + order.getId());
        }
        return order;
    }

    // Method to update an order's status (for simulating saga commit/rollback)
    public void updateStatus(Long orderId, String status) {
        Order order = orderStore.get(orderId);
        if (order != null) {
            order.setStatus(status); // Set the new status for the order
            System.out.println("Order status updated: " + orderId + " to " + status);
        } else {
            System.out.println("Order not found for status update: " + orderId);
        }
    }

    // Method to delete an order (useful for rolling back a saga or simulating removal)
    public void delete(Long orderId) {
        if (orderStore.containsKey(orderId)) {
            orderStore.remove(orderId);
            System.out.println("Order deleted: " + orderId);
        } else {
            System.out.println("Order not found to delete: " + orderId);
        }
    }
}
