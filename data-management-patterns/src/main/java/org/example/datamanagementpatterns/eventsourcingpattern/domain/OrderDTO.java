package org.example.datamanagementpatterns.eventsourcingpattern.domain;

public class OrderDTO {
    private Long id;
    private String status;

    public OrderDTO(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
