package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {
    private final CustomerProfileRepository customerProfileRepository;
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final VisitRecordRepository visitRecordRepository;
    private final TierUpgradeRuleRepository tierUpgradeRuleRepository;
    private final TierHistoryRecordRepository tierHistoryRecordRepository;
    
    public TierUpgradeEngineServiceImpl(CustomerProfileRepository customerProfileRepository,
                                        PurchaseRecordRepository purchaseRecordRepository,
                                        VisitRecordRepository visitRecordRepository,
                                        TierUpgradeRuleRepository tierUpgradeRuleRepository,
                                        TierHistoryRecordRepository tierHistoryRecordRepository) {
        this.customerProfileRepository = customerProfileRepository;
        this.purchaseRecordRepository = purchaseRecordRepository;
        this.visitRecordRepository = visitRecordRepository;
        this.tierUpgradeRuleRepository = tierUpgradeRuleRepository;
        this.tierHistoryRecordRepository = tierHistoryRecordRepository;
    }
    
    @Override
    public CustomerProfile evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerProfileRepository.findById(customerId)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        
        List<PurchaseRecord> purchases = purchaseRecordRepository.findByCustomerId(customerId);
        List<VisitRecord> visits = visitRecordRepository.findByCustomerId(customerId);
        
        double totalSpend = purchases.stream().mapToDouble(PurchaseRecord::getAmount).sum();
        int totalVisits = visits.size();
        
        String currentTier = customer.getCurrentTier();
        String newTier = determineNewTier(currentTier, totalSpend, totalVisits);
        
        if (!newTier.equals(currentTier)) {
            TierHistoryRecord history = new TierHistoryRecord(customerId, currentTier, newTier, "Automatic upgrade", null);
            tierHistoryRecordRepository.save(history);
            customer.setCurrentTier(newTier);
            return customerProfileRepository.save(customer);
        }
        
        return customer;
    }
    
    @Override
    public boolean checkTierUpgradeEligibility(Long customerId, String targetTier) {
        CustomerProfile customer = customerProfileRepository.findById(customerId)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        
        List<PurchaseRecord> purchases = purchaseRecordRepository.findByCustomerId(customerId);
        List<VisitRecord> visits = visitRecordRepository.findByCustomerId(customerId);
        
        double totalSpend = purchases.stream().mapToDouble(PurchaseRecord::getAmount).sum();
        int totalVisits = visits.size();
        
        return tierUpgradeRuleRepository.findByFromTierAndToTier(customer.getCurrentTier(), targetTier)
            .map(rule -> totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits())
            .orElse(false);
    }
    
    private String determineNewTier(String currentTier, double totalSpend, int totalVisits) {
        List<TierUpgradeRule> rules = tierUpgradeRuleRepository.findByActiveTrue();
        
        for (TierUpgradeRule rule : rules) {
            if (rule.getFromTier().equals(currentTier) && 
                totalSpend >= rule.getMinSpend() && 
                totalVisits >= rule.getMinVisits()) {
                return rule.getToTier();
            }
        }
        
        return currentTier;
    }
}