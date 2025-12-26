package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.service.TierUpgradeRuleService;

@Service
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {

    private final TierUpgradeRuleRepository repository;

    public TierUpgradeRuleServiceImpl(TierUpgradeRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TierUpgradeRule createRule(TierUpgradeRule rule) {
        if (rule.getMinSpend() < 0 || rule.getMinVisits() < 0) {
            throw new IllegalArgumentException("Invalid rule values");
        }
        return repository.save(rule);
    }

    @Override
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updated) {
        TierUpgradeRule existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));

        existing.setFromTier(updated.getFromTier());
        existing.setToTier(updated.getToTier());
        existing.setMinSpend(updated.getMinSpend());
        existing.setMinVisits(updated.getMinVisits());
        existing.setActive(updated.getActive());

        return repository.save(existing);
    }

    @Override
    public List<TierUpgradeRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public Optional<TierUpgradeRule> getRule(String fromTier, String toTier) {
        return repository.findByFromTierAndToTier(fromTier, toTier);
    }

    @Override
    public List<TierUpgradeRule> getAllRules() {
        return repository.findAll();
    }
}

