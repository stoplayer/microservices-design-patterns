package org.example.datamanagementpatterns.sagapattern.repository;

import org.example.datamanagementpatterns.sagapattern.model.SagaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaRepository extends JpaRepository<SagaEvent, Long> {
    // Here, you could define custom queries if needed in the future
}
