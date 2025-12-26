package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.VisitRecord;

@Repository
public interface VisitRecordRepository
        extends JpaRepository<VisitRecord, Long> {

    List<VisitRecord> findByCustomerId(Long customerId);

    List<VisitRecord> findByVisitDateBetween(
            LocalDate start, LocalDate end);
}
