package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tier-rules")
public class TierUpgradeRuleController {
    private final TierUpgradeRuleService tierUpgradeRuleService;
    
    public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
        this.tierUpgradeRuleService = tierUpgradeRuleService;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createRule(@RequestBody TierUpgradeRule tierUpgradeRule) {
        TierUpgradeRule saved = tierUpgradeRuleService.createTierUpgradeRule(tierUpgradeRule);
        return ResponseEntity.ok(new ApiResponse(true, "Tier upgrade rule created successfully", saved));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TierUpgradeRule> getRule(@PathVariable Long id) {
        TierUpgradeRule rule = tierUpgradeRuleService.getTierUpgradeRuleById(id);
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<TierUpgradeRule>> getActiveRules() {
        List<TierUpgradeRule> rules = tierUpgradeRuleService.getAllActiveTierUpgradeRules();
        return ResponseEntity.ok(rules);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateRule(@PathVariable Long id, @RequestBody TierUpgradeRule tierUpgradeRule) {
        TierUpgradeRule updated = tierUpgradeRuleService.updateTierUpgradeRule(id, tierUpgradeRule);
        return ResponseEntity.ok(new ApiResponse(true, "Tier upgrade rule updated successfully", updated));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRule(@PathVariable Long id) {
        tierUpgradeRuleService.deleteTierUpgradeRule(id);
        return ResponseEntity.ok(new ApiResponse(true, "Tier upgrade rule deleted successfully"));
    }
}