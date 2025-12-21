package com.example.demo.service;

import com.example.demo.entity.CustomerProfile;
import java.util.List;

public interface CustomerProfileService {
    CustomerProfile createCustomerProfile(CustomerProfile customerProfile);
    CustomerProfile getCustomerProfileById(Long id);
    CustomerProfile getCustomerProfileByCustomerId(String customerId);
    List<CustomerProfile> getAllCustomerProfiles();
    CustomerProfile updateCustomerProfile(Long id, CustomerProfile customerProfile);
    void deleteCustomerProfile(Long id);
}