package com.example.demo.service.impl;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.service.CustomerProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {
    private final CustomerProfileRepository customerProfileRepository;
    
    public CustomerProfileServiceImpl(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }
    
    @Override
    public CustomerProfile createCustomerProfile(CustomerProfile customerProfile) {
        if (customerProfileRepository.findByCustomerId(customerProfile.getCustomerId()).isPresent()) {
            throw new IllegalArgumentException("Customer ID already exists");
        }
        if (customerProfileRepository.findByEmail(customerProfile.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return customerProfileRepository.save(customerProfile);
    }
    
    @Override
    public CustomerProfile getCustomerProfileById(Long id) {
        return customerProfileRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    
    @Override
    public CustomerProfile getCustomerProfileByCustomerId(String customerId) {
        return customerProfileRepository.findByCustomerId(customerId)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    
    @Override
    public List<CustomerProfile> getAllCustomerProfiles() {
        return customerProfileRepository.findAll();
    }
    
    @Override
    public CustomerProfile updateCustomerProfile(Long id, CustomerProfile customerProfile) {
        CustomerProfile existing = getCustomerProfileById(id);
        existing.setFullName(customerProfile.getFullName());
        existing.setEmail(customerProfile.getEmail());
        existing.setPhone(customerProfile.getPhone());
        existing.setCurrentTier(customerProfile.getCurrentTier());
        existing.setActive(customerProfile.getActive());
        return customerProfileRepository.save(existing);
    }
    
    @Override
    public void deleteCustomerProfile(Long id) {
        if (!customerProfileRepository.existsById(id)) {
            throw new NoSuchElementException("Customer not found");
        }
        customerProfileRepository.deleteById(id);
    }
}