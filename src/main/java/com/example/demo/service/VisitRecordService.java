package com.example.demo.service;

import com.example.demo.entity.VisitRecord;
import java.time.LocalDate;
import java.util.List;

public interface VisitRecordService {
    VisitRecord createVisitRecord(VisitRecord visitRecord);
    List<VisitRecord> getVisitRecordsByCustomerId(Long customerId);
    List<VisitRecord> getVisitRecordsByDateRange(LocalDate start, LocalDate end);
}