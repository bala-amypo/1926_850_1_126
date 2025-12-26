package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;

@RestController
@RequestMapping("/api/tier-rules")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService service;

    public TierUpgradeRuleController(TierUpgradeRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TierUpgradeRule create(@RequestBody TierUpgradeRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public TierUpgradeRule update(
            @PathVariable Long id,
            @RequestBody TierUpgradeRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping
    public List<TierUpgradeRule> getAll() {
        return service.getAllRules();
    }

    @GetMapping("/active")
    public List<TierUpgradeRule> getActive() {
        return service.getActiveRules();
    }

    @GetMapping("/lookup")
    public Optional<TierUpgradeRule> lookup(
            @RequestParam String fromTier,
            @RequestParam String toTier) {
        return service.getRule(fromTier, toTier);
    }
}
