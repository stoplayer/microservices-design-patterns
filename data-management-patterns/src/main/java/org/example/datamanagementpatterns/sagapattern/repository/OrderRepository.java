package org.example.datamanagementpatterns.sagapattern.repository;

import org.example.datamanagementpatterns.sagapattern.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
