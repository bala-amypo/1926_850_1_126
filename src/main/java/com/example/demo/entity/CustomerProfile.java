package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String customerId;
    
    private String fullName;
    
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String phone;
    
    private String currentTier = "BRONZE";
    
    private Boolean active = true;
    
    private LocalDateTime createdAt;

    // --- ADDED FIELDS FOR TIER UPGRADE LOGIC ---
    private Double totalSpend = 0.0;
    private Integer totalVisits = 0;
    // ------------------------------------------

    public CustomerProfile() {}
    
    public CustomerProfile(String customerId, String fullName, String email, String phone, String currentTier, Boolean active, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.currentTier = currentTier != null ? currentTier : "BRONZE";
        this.active = active != null ? active : true;
        this.createdAt = createdAt;
    }
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getCurrentTier() { return currentTier; }
    public void setCurrentTier(String currentTier) { this.currentTier = currentTier; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // --- ADDED GETTERS AND SETTERS ---
    public Double getTotalSpend() { return totalSpend; }
    public void setTotalSpend(Double totalSpend) { this.totalSpend = totalSpend; }

    public Integer getTotalVisits() { return totalVisits; }
    public void setTotalVisits(Integer totalVisits) { this.totalVisits = totalVisits; }
}