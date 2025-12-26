package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;

@RestController
@RequestMapping("/api/tier-engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService service;

    public TierUpgradeEngineController(TierUpgradeEngineService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{customerId}")
    public TierHistoryRecord evaluate(
            @PathVariable Long customerId) {
        return service.evaluateAndUpgradeTier(customerId);
    }

    @GetMapping("/history/{customerId}")
    public List<TierHistoryRecord> historyByCustomer(
            @PathVariable Long customerId) {
        return service.getHistoryByCustomer(customerId);
    }

    @GetMapping
    public List<TierHistoryRecord> getAllHistory() {
        return service.getAllHistory();
    }
}
