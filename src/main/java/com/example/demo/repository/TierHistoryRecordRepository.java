package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TierHistoryRecord;

@Repository
public interface TierHistoryRecordRepository
        extends JpaRepository<TierHistoryRecord, Long> {

    List<TierHistoryRecord> findByCustomerId(Long customerId);

    List<TierHistoryRecord> findByChangedAtBetween(
            LocalDateTime start, LocalDateTime end);
}
