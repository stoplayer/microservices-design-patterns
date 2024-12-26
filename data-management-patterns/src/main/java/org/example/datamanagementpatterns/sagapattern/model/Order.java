package org.example.datamanagementpatterns.sagapattern.model;

public class Order {

    private Long orderId;
    private String status;
    private Double amount;

    // Constructor
    public Order(Long orderId, String status, Double amount) {
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
