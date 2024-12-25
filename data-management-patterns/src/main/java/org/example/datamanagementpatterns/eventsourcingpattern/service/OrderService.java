package org.example.datamanagementpatterns.eventsourcingpattern.service;

import org.example.datamanagementpatterns.domain.Order;
import org.example.datamanagementpatterns.domain.OrderDTO;

import org.example.datamanagementpatterns.eventsourcingpattern.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(Long orderId) {
        Order order = new Order(orderId);
        order.createOrder();
        orderRepository.save(order);
    }

    public void confirmOrder(Long orderId) {
        Order order = orderRepository.find(orderId);
        if (order != null) {
            order.confirmOrder();
            orderRepository.save(order);
        }
    }
}
