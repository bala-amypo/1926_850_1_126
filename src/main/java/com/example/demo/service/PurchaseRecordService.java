package com.example.demo.service;

import com.example.demo.entity.PurchaseRecord;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseRecordService {
    PurchaseRecord createPurchaseRecord(PurchaseRecord purchaseRecord);
    List<PurchaseRecord> getPurchaseRecordsByCustomerId(Long customerId);
    List<PurchaseRecord> getPurchaseRecordsByDateRange(LocalDate start, LocalDate end);
}   