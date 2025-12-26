package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordRepository repository;

    public VisitRecordServiceImpl(VisitRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        if (!List.of("STORE", "APP", "WEB").contains(visit.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return repository.save(visit);
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return repository.findAll();
    }

    @Override
    public Optional<VisitRecord> getVisitById(Long id) {
        return repository.findById(id);
    }
}

