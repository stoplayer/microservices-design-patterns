package org.example.datamanagementpatterns.sagapattern;

import org.example.datamanagementpatterns.sagapattern.service.SagaManager;
import org.example.datamanagementpatterns.sagapattern.service.SagaPatternService;
import org.example.datamanagementpatterns.sagapattern.model.Order; // Assuming you have an Order model
import org.example.datamanagementpatterns.sagapattern.repository.OrderRepository; // Assuming you have a repository for Order
import org.junit.jupiter.api.Test; // JUnit Test annotation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*; // For assertions like assertEquals
import static org.mockito.Mockito.*; // For mocking and verifying calls

@SpringBootTest
class DataManagementPatternsApplicationTests {

	@Autowired
	private SagaManager sagaManager; // If you use a SagaManager for orchestration, it’s included here.

	@Autowired
	private SagaPatternService sagaPatternService;

	@MockBean
	private OrderRepository orderRepository; // Mock the OrderRepository to avoid actual database calls

	@Test
	void testSuccessfulSaga() throws InterruptedException {
		// Create a sample order
		Order order = new Order(1L, "Pending", 100.00);

		// Mock the repository save behavior
		when(orderRepository.save(any(Order.class))).thenReturn(order);

		// Simulate starting the saga
		System.out.println("Starting the Saga...");
		sagaPatternService.createSaga(order);  // Initiating Saga through SagaPatternService

		// Simulate a successful order creation (commit)
		System.out.println("Committing the Saga...");
		sagaPatternService.commitSaga(order);  // Committing the Saga
		System.out.println("Saga Committed. Order Created Successfully.");

		// Assert the order status
		assertEquals("Confirmed", order.getStatus(), "Order should be confirmed after the saga commit");

		// Verify that the repository's save method was called once during the saga
		verify(orderRepository, times(1)).save(order);
	}

	@Test
	void testFailedSaga() throws InterruptedException {
		// Create a sample order
		Order order = new Order(2L, "Pending", 200.00);

		// Mock the repository save behavior
		when(orderRepository.save(any(Order.class))).thenReturn(order);

		// Simulate starting the saga
		System.out.println("Starting the Saga...");
		sagaPatternService.createSaga(order);  // Initiating Saga through SagaPatternService

		// Simulate a failure scenario
		try {
			System.out.println("Simulating Failure Scenario...");
			throw new RuntimeException("Payment failed");  // Simulate payment failure
		} catch (RuntimeException e) {
			System.out.println("Error during saga: " + e.getMessage());
			sagaPatternService.rollbackSaga(order);  // Rolling back the Saga
			System.out.println("Saga Rolled Back.");

			// Assert the order status after rollback
			assertEquals("Failed", order.getStatus(), "Order should be marked as failed after rollback");
		}

		// Verify that the repository's save method was still called during the saga attempt
		verify(orderRepository, times(1)).save(order);
	}
}
