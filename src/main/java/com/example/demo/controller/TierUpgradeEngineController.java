package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tier-engine")
public class TierUpgradeEngineController {
    private final TierUpgradeEngineService tierUpgradeEngineService;
    
    public TierUpgradeEngineController(TierUpgradeEngineService tierUpgradeEngineService) {
        this.tierUpgradeEngineService = tierUpgradeEngineService;
    }
    
    @PostMapping("/evaluate/{customerId}")
    public ResponseEntity<ApiResponse> evaluateTierUpgrade(@PathVariable Long customerId) {
        CustomerProfile customer = tierUpgradeEngineService.evaluateAndUpgradeTier(customerId);
        return ResponseEntity.ok(new ApiResponse(true, "Tier evaluation completed", customer));
    }
    
    @GetMapping("/eligibility/{customerId}/{targetTier}")
    public ResponseEntity<ApiResponse> checkEligibility(@PathVariable Long customerId, @PathVariable String targetTier) {
        boolean eligible = tierUpgradeEngineService.checkTierUpgradeEligibility(customerId, targetTier);
        return ResponseEntity.ok(new ApiResponse(true, "Eligibility check completed", eligible));
    }
}