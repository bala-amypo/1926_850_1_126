package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.CustomerProfile;

public interface CustomerProfileService {

    CustomerProfile createCustomer(CustomerProfile customer);

    CustomerProfile getCustomerById(Long id);

    Optional<CustomerProfile> findByCustomerId(String customerId);

    List<CustomerProfile> getAllCustomers();

    CustomerProfile updateTier(Long id, String newTier);

    CustomerProfile updateStatus(Long id, boolean active);
}
