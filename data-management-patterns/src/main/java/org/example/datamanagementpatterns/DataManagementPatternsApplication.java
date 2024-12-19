package org.example.datamanagementpatterns;

import org.example.datamanagementpatterns.repository.OrderRepository;
import org.example.datamanagementpatterns.saga.common.SagaManager;
import org.example.datamanagementpatterns.saga.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataManagementPatternsApplicationTests {

	@Autowired
	private SagaManager sagaManager;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CreateOrderSaga createOrderSaga;

	@Test
	void testSuccessfulSaga() throws InterruptedException {
		// Create a sample order
		Order order = new Order(1L, "Pending", 100.00);

		// Create the saga instance
		createOrderSaga = new CreateOrderSaga(order);

		// Simulate starting the saga
		System.out.println("Starting the Saga...");
		sagaManager.create(createOrderSaga);

		// Simulate a successful order creation (commit)
		System.out.println("Committing the Saga...");
		sagaManager.commit(createOrderSaga);
		System.out.println("Saga Committed. Order Created Successfully.");

		// Assert the order status
		assertEquals("Confirmed", order.getStatus(), "Order should be confirmed after the saga commit");
	}

	@Test
	void testFailedSaga() throws InterruptedException {
		// Create a sample order
		Order order = new Order(2L, "Pending", 200.00);

		// Create the saga instance
		createOrderSaga = new CreateOrderSaga(order);

		// Simulate starting the saga
		System.out.println("Starting the Saga...");
		sagaManager.create(createOrderSaga);

		// Simulate a failure scenario
		try {
			System.out.println("Simulating Failure Scenario...");
			throw new RuntimeException("Payment failed"); // Simulate payment failure
		} catch (RuntimeException e) {
			System.out.println("Error during saga: " + e.getMessage());
			sagaManager.rollback(createOrderSaga);  // Rollback the saga
			System.out.println("Saga Rolled Back.");

			// Assert the order status
			assertEquals("Failed", order.getStatus(), "Order should be marked as failed after rollback");
		}
	}
}
