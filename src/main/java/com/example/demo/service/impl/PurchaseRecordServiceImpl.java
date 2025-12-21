package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {
    private final PurchaseRecordRepository purchaseRecordRepository;
    
    public PurchaseRecordServiceImpl(PurchaseRecordRepository purchaseRecordRepository) {
        this.purchaseRecordRepository = purchaseRecordRepository;
    }
    
    @Override
    public PurchaseRecord createPurchaseRecord(PurchaseRecord purchaseRecord) {
        if (purchaseRecord.getAmount() == null || purchaseRecord.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        System.out.println("Creating purchase: CustomerId=" + purchaseRecord.getCustomerId() + ", Amount=" + purchaseRecord.getAmount());
        PurchaseRecord saved = purchaseRecordRepository.save(purchaseRecord);
        System.out.println("Saved purchase with ID: " + saved.getId());
        return saved;
    }
    
    @Override
    public List<PurchaseRecord> getPurchaseRecordsByCustomerId(Long customerId) {
        return purchaseRecordRepository.findByCustomerId(customerId);
    }
    
    @Override
    public List<PurchaseRecord> getPurchaseRecordsByDateRange(LocalDate start, LocalDate end) {
        return purchaseRecordRepository.findByPurchaseDateBetween(start, end);
    }
}