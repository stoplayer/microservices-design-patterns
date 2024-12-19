package org.example.datamanagementpatterns;

import org.example.datamanagementpatterns.domain.Order;
import org.example.datamanagementpatterns.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;
    private final SagaManager sagaManager;

    // Constructor to inject dependencies
    public OrderService(OrderRepository orderRepository, SagaManager sagaManager) {
        this.orderRepository = orderRepository;
        this.sagaManager = sagaManager;
    }

    // Method to create a new order and start the saga
    public Order createOrder(Order order) throws InterruptedException {
        // Persist the order first
        orderRepository.save(order);

        // Start the saga to manage the distributed transaction (e.g., stock reservation, payment)
        sagaManager.create(new CreateOrderSaga(order));

        return order;
    }

    // Method to update an existing order
    public Order updateOrder(Long orderId, Order updatedOrder) {
        // Find the existing order
        Order existingOrder = orderRepository.findOne(orderId);

        // Update the order details
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());

        // Persist the updated order
        orderRepository.save(existingOrder);

        // Optionally, you can start or update a saga here if needed for the update process
        // sagaManager.create(new UpdateOrderSaga(existingOrder));

        return existingOrder;
    }
}
