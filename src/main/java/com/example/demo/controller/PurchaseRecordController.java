package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRecordController {

    private final PurchaseRecordService service;

    public PurchaseRecordController(PurchaseRecordService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseRecord create(@RequestBody PurchaseRecord purchase) {
        return service.recordPurchase(purchase);
    }

    @GetMapping("/{id}")
    public Optional<PurchaseRecord> getById(@PathVariable Long id) {
        return service.getPurchaseById(id);
    }

    @GetMapping
    public List<PurchaseRecord> getAll() {
        return service.getAllPurchases();
    }

    @GetMapping("/customer/{customerId}")
    public List<PurchaseRecord> getByCustomer(
            @PathVariable Long customerId) {
        return service.getPurchasesByCustomer(customerId);
    }
}
