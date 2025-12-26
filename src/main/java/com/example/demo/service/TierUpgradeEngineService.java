package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TierHistoryRecord;

public interface TierUpgradeEngineService {

    TierHistoryRecord evaluateAndUpgradeTier(Long customerId);

    List<TierHistoryRecord> getHistoryByCustomer(Long customerId);

    List<TierHistoryRecord> getAllHistory();
}
