package com.example.demo.service;

import com.example.demo.entity.CustomerProfile;

public interface TierUpgradeEngineService {
    CustomerProfile evaluateAndUpgradeTier(Long customerId);
    boolean checkTierUpgradeEligibility(Long customerId, String targetTier);
}