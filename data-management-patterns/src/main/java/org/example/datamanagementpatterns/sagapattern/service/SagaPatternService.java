package org.example.datamanagementpatterns.sagapattern.service;

import org.example.datamanagementpatterns.sagapattern.model.Order;
import org.example.datamanagementpatterns.sagapattern.model.SagaEvent;
import org.example.datamanagementpatterns.sagapattern.repository.SagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaPatternService {

    @Autowired
    private SagaRepository sagaRepository;

    public String applySagaPattern(String userCode) {
        // Apply Saga Pattern logic to the code
        String modifiedCode = userCode + "\n// Saga Pattern Applied\n// Example of Saga logic";

        // Example of saving an event related to Saga pattern
        SagaEvent event = new SagaEvent();
        event.setEventName("SagaPatternApplied");
        event.setEventData("Modified code with Saga pattern");
        sagaRepository.save(event);  // Save Saga event to the database

        return modifiedCode;
    }

    public String modifyCodeWithPattern(String userCode) {
        return userCode;
    }

    public void createSaga(Order order) {
    }

    public void commitSaga(Order order) {
    }

    public void rollbackSaga(Order order) {
    }
}
