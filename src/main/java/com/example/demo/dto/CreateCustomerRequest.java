package com.example.demo.dto;

public class CreateCustomerRequest {
    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    
    public CreateCustomerRequest() {}
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}