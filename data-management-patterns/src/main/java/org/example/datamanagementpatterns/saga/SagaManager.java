package org.example.datamanagementpatterns;

import org.example.datamanagementpatterns.common.Saga;

@Component
public class SagaManager {
    // Method to create (start) the saga
    public void create(Saga saga) throws InterruptedException {
        System.out.println("Starting the saga...");
        saga.start();  // Start the saga
    }

    // Method to commit (finalize) the saga once all steps are completed successfully
    public void commit(Saga saga) {
        System.out.println("Committing the saga...");
        saga.commit();  // Commit the saga if everything is successful
    }

    // Method to rollback the saga in case of failure
    public void rollback(Saga saga) {
        System.out.println("Rolling back the saga...");
        saga.rollback();  // Rollback the saga if something goes wrong
    }
}
