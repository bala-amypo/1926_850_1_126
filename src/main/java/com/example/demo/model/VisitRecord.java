package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Entity
@Table(name = "visit_record")
public class VisitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    private LocalDate visitDate;
    private String channel;

    public VisitRecord() {}

    public void setId(Long id) { this.id = id; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }
    public void setChannel(String channel) { this.channel = channel; }

    public void setCustomer(CustomerProfile customer) {
        this.customerId = customer.getId();
    }

    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public LocalDate getVisitDate() { return visitDate; }
    public String getChannel() { return channel; }
}