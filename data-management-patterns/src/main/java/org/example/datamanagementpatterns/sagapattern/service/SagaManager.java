package org.example.datamanagementpatterns.sagapattern.service;

import org.example.datamanagementpatterns.sagapattern.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaManager {

    @Autowired
    private SagaPatternService sagaPatternService;  // Injecting the SagaPatternService

    public void startSaga(Order order) {
        try {
            // Step 1: Start the saga process by calling the createSaga method
            sagaPatternService.createSaga(order);

            // Step 2: Commit the saga after successful operations
            sagaPatternService.commitSaga(order);

            System.out.println("Saga Completed Successfully!");

        } catch (Exception e) {
            // Step 3: In case of any failure, handle rollback (compensation)
            System.out.println("Error occurred, rolling back saga...");
            sagaPatternService.rollbackSaga(order);
        }
    }
}
