package org.example.datamanagementpatterns;

import org.example.datamanagementpatterns.saga.domain.Order;
import org.example.datamanagementpatterns.repository.OrderRepository;
import org.example.datamanagementpatterns.saga.common.Saga;


@Component
public class CreateOrderSaga implements Saga {
    private final Order order;
    private final OrderRepository orderRepository; // Add repository for persistence

    public CreateOrderSaga(Order order, OrderRepository orderRepository) {
        this.order = order;
        this.orderRepository = orderRepository;
    }

    @Override
    public void start() {
        // Implement the saga steps
        try {
            // Step 1: Reserve Inventory (simulate this)
            boolean inventoryReserved = reserveInventory();
            if (!inventoryReserved) {
                throw new RuntimeException("Failed to reserve inventory.");
            }

            // Step 2: Process Payment (simulate this)
            boolean paymentProcessed = processPayment();
            if (!paymentProcessed) {
                throw new RuntimeException("Payment processing failed.");
            }

            // Step 3: Create the Order (simulate saving to database)
            confirmOrderCreation();

        } catch (Exception e) {
            // In case of any exception, trigger rollback
            rollback();
        }
    }

    @Override
    public void commit() {
        // Finalize the order creation
        // E.g., Mark order as confirmed in the database
        System.out.println("Order confirmed: " + order.getId());
        order.setStatus("Confirmed");

        // Persist the updated order in the repository
        orderRepository.save(order); // Persist the confirmed order
    }

    @Override
    public void rollback() {
        // Rollback any changes made during the saga steps
        // For example, cancel the payment and release inventory
        cancelPayment();
        releaseInventory();

        // Update the order status to "Failed" or "Cancelled"
        System.out.println("Order creation failed: " + order.getId());
        order.setStatus("Failed");

        // Persist the updated order status in the repository
        orderRepository.save(order); // Persist the failed order status
    }

    // Simulate inventory reservation
    private boolean reserveInventory() {
        // Simulate checking inventory
        System.out.println("Reserving inventory for order: " + order.getId());
        return true; // Assume success for simplicity
    }

    // Simulate payment processing
    private boolean processPayment() {
        // Simulate payment processing
        System.out.println("Processing payment for order: " + order.getId());
        return true; // Assume success for simplicity
    }

    // Simulate confirming order creation in the system
    private void confirmOrderCreation() {
        // Simulate order creation in the database
        System.out.println("Creating order: " + order.getId());
        commit(); // Call commit once everything is successful
    }

    // Simulate canceling the payment
    private void cancelPayment() {
        System.out.println("Canceling payment for order: " + order.getId());
    }

    // Simulate releasing the reserved inventory
    private void releaseInventory() {
        System.out.println("Releasing reserved inventory for order: " + order.getId());
    }
}
