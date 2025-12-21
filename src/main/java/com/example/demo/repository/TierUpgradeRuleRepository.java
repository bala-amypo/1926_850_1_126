package com.example.demo.repository;

import com.example.demo.entity.TierUpgradeRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TierUpgradeRuleRepository extends JpaRepository<TierUpgradeRule, Long> {
    Optional<TierUpgradeRule> findByFromTierAndToTier(String fromTier, String toTier);
    List<TierUpgradeRule> findByActiveTrue();
}