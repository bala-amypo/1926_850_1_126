package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.model.TierUpgradeRule;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.repository.TierHistoryRecordRepository;
import com.example.demo.service.TierUpgradeEngineService;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    public TierUpgradeEngineServiceImpl(CustomerProfileRepository customerRepo,
                                        PurchaseRecordRepository purchaseRepo,
                                        VisitRecordRepository visitRepo,
                                        TierUpgradeRuleRepository ruleRepo,
                                        TierHistoryRecordRepository historyRepo) {
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
        this.visitRepo = visitRepo;
        this.ruleRepo = ruleRepo;
        this.historyRepo = historyRepo;
    }

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        var customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Customer not found"));

        double totalSpend = purchaseRepo.findByCustomerId(customerId)
                .stream().mapToDouble(p -> p.getAmount()).sum();

        int totalVisits = visitRepo.findByCustomerId(customerId).size();

        for (TierUpgradeRule rule : ruleRepo.findByActiveTrue()) {
            if (rule.getFromTier().equals(customer.getCurrentTier())
                    && totalSpend >= rule.getMinSpend()
                    && totalVisits >= rule.getMinVisits()) {

                String oldTier = customer.getCurrentTier();
                customer.setCurrentTier(rule.getToTier());
                customerRepo.save(customer);

                TierHistoryRecord history = new TierHistoryRecord(
                        customerId,
                        oldTier,
                        rule.getToTier(),
                        "Auto upgrade",
                        LocalDateTime.now()
                );
                return historyRepo.save(history);
            }
        }
        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return historyRepo.findByCustomerId(customerId);
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return historyRepo.findAll();
    }
}

