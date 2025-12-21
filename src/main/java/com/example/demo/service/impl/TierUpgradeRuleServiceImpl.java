package com.example.demo.service.impl;

import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {
    private final TierUpgradeRuleRepository tierUpgradeRuleRepository;
    
    public TierUpgradeRuleServiceImpl(TierUpgradeRuleRepository tierUpgradeRuleRepository) {
        this.tierUpgradeRuleRepository = tierUpgradeRuleRepository;
    }
    
    @Override
    public TierUpgradeRule createTierUpgradeRule(TierUpgradeRule tierUpgradeRule) {
        if (tierUpgradeRule.getMinSpend() < 0 || tierUpgradeRule.getMinVisits() < 0) {
            throw new IllegalArgumentException("Minimum spend and visits must be >= 0");
        }
        return tierUpgradeRuleRepository.save(tierUpgradeRule);
    }
    
    @Override
    public TierUpgradeRule getTierUpgradeRuleById(Long id) {
        return tierUpgradeRuleRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Rule not found"));
    }
    
    @Override
    public List<TierUpgradeRule> getAllActiveTierUpgradeRules() {
        return tierUpgradeRuleRepository.findByActiveTrue();
    }
    
    @Override
    public TierUpgradeRule updateTierUpgradeRule(Long id, TierUpgradeRule tierUpgradeRule) {
        TierUpgradeRule existing = getTierUpgradeRuleById(id);
        existing.setFromTier(tierUpgradeRule.getFromTier());
        existing.setToTier(tierUpgradeRule.getToTier());
        existing.setMinSpend(tierUpgradeRule.getMinSpend());
        existing.setMinVisits(tierUpgradeRule.getMinVisits());
        existing.setActive(tierUpgradeRule.getActive());
        return tierUpgradeRuleRepository.save(existing);
    }
    
    @Override
    public void deleteTierUpgradeRule(Long id) {
        if (!tierUpgradeRuleRepository.existsById(id)) {
            throw new NoSuchElementException("Rule not found");
        }
        tierUpgradeRuleRepository.deleteById(id);
    }
}