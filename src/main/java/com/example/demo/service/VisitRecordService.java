package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.VisitRecord;

public interface VisitRecordService {

    VisitRecord recordVisit(VisitRecord visit);

    List<VisitRecord> getVisitsByCustomer(Long customerId);

    List<VisitRecord> getAllVisits();

    Optional<VisitRecord> getVisitById(Long id);
}
