package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerProfileController {
    private final CustomerProfileService customerProfileService;
    
    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerProfile customerProfile) {
        CustomerProfile saved = customerProfileService.createCustomerProfile(customerProfile);
        return ResponseEntity.ok(new ApiResponse(true, "Customer created successfully", saved));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfile> getCustomer(@PathVariable Long id) {
        CustomerProfile customer = customerProfileService.getCustomerProfileById(id);
        return ResponseEntity.ok(customer);
    }
    
    @GetMapping
    public ResponseEntity<List<CustomerProfile>> getAllCustomers() {
        List<CustomerProfile> customers = customerProfileService.getAllCustomerProfiles();
        return ResponseEntity.ok(customers);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerProfile customerProfile) {
        CustomerProfile updated = customerProfileService.updateCustomerProfile(id, customerProfile);
        return ResponseEntity.ok(new ApiResponse(true, "Customer updated successfully", updated));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        customerProfileService.deleteCustomerProfile(id);
        return ResponseEntity.ok(new ApiResponse(true, "Customer deleted successfully"));
    }
}