package org.example.datamanagementpatterns.domain;

public class Order {
    private Long id;
    private String status;
    private Double totalAmount;

    // Constructor
    public Order(Long id, String status, Double totalAmount) {
        this.id = id;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Default constructor
    public Order() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Optionally, override toString(), equals(), and hashCode() if needed
    @Override
    public String toString() {
        return "Order{id=" + id + ", status='" + status + "', totalAmount=" + totalAmount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
