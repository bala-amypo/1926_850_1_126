package com.example.demo.service;

import com.example.demo.entity.TierUpgradeRule;
import java.util.List;

public interface TierUpgradeRuleService {
    TierUpgradeRule createTierUpgradeRule(TierUpgradeRule tierUpgradeRule);
    TierUpgradeRule getTierUpgradeRuleById(Long id);
    List<TierUpgradeRule> getAllActiveTierUpgradeRules();
    TierUpgradeRule updateTierUpgradeRule(Long id, TierUpgradeRule tierUpgradeRule);
    void deleteTierUpgradeRule(Long id);
}