package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.PurchaseRecord;

public interface PurchaseRecordService {

    PurchaseRecord recordPurchase(PurchaseRecord purchase);

    List<PurchaseRecord> getPurchasesByCustomer(Long customerId);

    List<PurchaseRecord> getAllPurchases();

    Optional<PurchaseRecord> getPurchaseById(Long id);
}
