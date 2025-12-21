package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.VisitRecord;
import com.example.demo.service.VisitRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitRecordController {
    private final VisitRecordService visitRecordService;
    
    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createVisit(@RequestBody VisitRecord visitRecord) {
        VisitRecord saved = visitRecordService.createVisitRecord(visitRecord);
        return ResponseEntity.ok(new ApiResponse(true, "Visit record created successfully", saved));
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VisitRecord>> getVisitsByCustomer(@PathVariable Long customerId) {
        List<VisitRecord> visits = visitRecordService.getVisitRecordsByCustomerId(customerId);
        return ResponseEntity.ok(visits);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<VisitRecord>> getVisitsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<VisitRecord> visits = visitRecordService.getVisitRecordsByDateRange(start, end);
        return ResponseEntity.ok(visits);
    }
}