package com.example.demo.service.impl;

import com.example.demo.entity.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {
    private final VisitRecordRepository visitRecordRepository;
    private final List<String> validChannels = Arrays.asList("STORE", "APP", "WEB");
    
    public VisitRecordServiceImpl(VisitRecordRepository visitRecordRepository) {
        this.visitRecordRepository = visitRecordRepository;
    }
    
    @Override
    public VisitRecord createVisitRecord(VisitRecord visitRecord) {
        if (!validChannels.contains(visitRecord.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return visitRecordRepository.save(visitRecord);
    }
    
    @Override
    public List<VisitRecord> getVisitRecordsByCustomerId(Long customerId) {
        return visitRecordRepository.findByCustomerId(customerId);
    }
    
    @Override
    public List<VisitRecord> getVisitRecordsByDateRange(LocalDate start, LocalDate end) {
        return visitRecordRepository.findByVisitDateBetween(start, end);
    }
}