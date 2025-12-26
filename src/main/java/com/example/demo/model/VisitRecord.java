package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visit_records")
public class VisitRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long customerId;
    
    private LocalDate visitDate;
    
    private String channel;
    
    public VisitRecord() {}
    
    public VisitRecord(Long customerId, LocalDate visitDate, String channel) {
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.channel = channel;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }
    
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
}