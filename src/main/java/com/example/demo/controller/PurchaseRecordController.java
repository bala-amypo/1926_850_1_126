package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseRecordController {
    private final PurchaseRecordService purchaseRecordService;
    
    public PurchaseRecordController(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> createPurchase(@RequestBody PurchaseRecord purchaseRecord) {
        PurchaseRecord saved = purchaseRecordService.createPurchaseRecord(purchaseRecord);
        return ResponseEntity.ok(new ApiResponse(true, "Purchase record created successfully", saved));
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PurchaseRecord>> getPurchasesByCustomer(@PathVariable Long customerId) {
        List<PurchaseRecord> purchases = purchaseRecordService.getPurchaseRecordsByCustomerId(customerId);
        return ResponseEntity.ok(purchases);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<PurchaseRecord>> getPurchasesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<PurchaseRecord> purchases = purchaseRecordService.getPurchaseRecordsByDateRange(start, end);
        return ResponseEntity.ok(purchases);
    }
}