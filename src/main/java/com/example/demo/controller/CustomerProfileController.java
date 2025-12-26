package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CustomerProfile;
import com.example.demo.service.CustomerProfileService;

@RestController
@RequestMapping("/api/customers")
public class CustomerProfileController {

    private final CustomerProfileService service;

    public CustomerProfileController(CustomerProfileService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerProfile create(@RequestBody CustomerProfile customer) {
        return service.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerProfile getById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerProfile> getAll() {
        return service.getAllCustomers();
    }

    @PutMapping("/{id}/tier")
    public CustomerProfile updateTier(
            @PathVariable Long id,
            @RequestParam String newTier) {
        return service.updateTier(id, newTier);
    }

    @PutMapping("/{id}/status")
    public CustomerProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return service.updateStatus(id, active);
    }

    @GetMapping("/lookup/{customerId}")
    public Optional<CustomerProfile> findByCustomerId(
            @PathVariable String customerId) {
        return service.findByCustomerId(customerId);
    }
}
