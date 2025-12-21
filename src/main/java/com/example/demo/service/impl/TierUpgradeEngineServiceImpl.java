package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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
        
        double totalSpend = purchases.stream()
            .filter(p -> p.getAmount() != null)
            .mapToDouble(PurchaseRecord::getAmount)
            .sum();
        int totalVisits = visits.size();
        
        System.out.println("Customer ID: " + customerId);
        System.out.println("Found " + purchases.size() + " purchases");
        System.out.println("Total Spend: " + totalSpend);
        System.out.println("Total Visits: " + totalVisits);
        
        String currentTier = customer.getCurrentTier();
        String newTier = determineNewTier(currentTier, totalSpend, totalVisits);
        
        if (!newTier.equals(currentTier)) {
            TierHistoryRecord history = new TierHistoryRecord(customerId, currentTier, newTier, "Automatic upgrade", null);
            tierHistoryRecordRepository.save(history);
            customer.setCurrentTier(newTier);
        }
        
        // Update total spend and visits
        customer.setTotalSpend(totalSpend);
        customer.setTotalVisits(totalVisits);
        return customerProfileRepository.save(customer);
    }
    
    @Override
    public boolean checkTierUpgradeEligibility(Long customerId, String targetTier) {
        CustomerProfile customer = customerProfileRepository.findById(customerId)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        
        List<PurchaseRecord> purchases = purchaseRecordRepository.findByCustomerId(customerId);
        double totalSpend = purchases.stream()
            .filter(p -> p.getAmount() != null)
            .mapToDouble(PurchaseRecord::getAmount)
            .sum();
        
        String eligibleTier = determineNewTier(customer.getCurrentTier(), totalSpend, 0);
        return eligibleTier.equals(targetTier) || isHigherTier(eligibleTier, targetTier);
    }
    
    private boolean isHigherTier(String tier1, String tier2) {
        int tier1Level = getTierLevel(tier1);
        int tier2Level = getTierLevel(tier2);
        return tier1Level >= tier2Level;
    }
    
    private int getTierLevel(String tier) {
        switch (tier) {
            case "BRONZE": return 1;
            case "SILVER": return 2;
            case "GOLD": return 3;
            default: return 0;
        }
    }
    
    private String determineNewTier(String currentTier, double totalSpend, int totalVisits) {
        if (totalSpend >= 20000) {
            return "GOLD";
        } else if (totalSpend >= 5000) {
            return "SILVER";
        } else {
            return "BRONZE";
        }
    }
}