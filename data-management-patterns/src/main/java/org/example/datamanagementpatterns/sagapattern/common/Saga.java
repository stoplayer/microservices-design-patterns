package org.example.datamanagementpatterns.common;

public interface Saga {
    void start();      // Method to start the saga
    void commit();     // Method to commit the saga (finalize)
    void rollback();   // Method to rollback the saga if something fails
}
