package com.example.demo.repository;

import com.example.demo.entity.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    List<PurchaseRecord> findByCustomerId(Long customerId);
    List<PurchaseRecord> findByPurchaseDateBetween(LocalDate start, LocalDate end);
}