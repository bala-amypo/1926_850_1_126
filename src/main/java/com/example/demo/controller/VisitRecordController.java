package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;

@RestController
@RequestMapping("/api/visits")
public class VisitRecordController {

    private final VisitRecordService service;

    public VisitRecordController(VisitRecordService service) {
        this.service = service;
    }

    @PostMapping
    public VisitRecord create(@RequestBody VisitRecord visit) {
        return service.recordVisit(visit);
    }

    @GetMapping("/{id}")
    public Optional<VisitRecord> getById(@PathVariable Long id) {
        return service.getVisitById(id);
    }

    @GetMapping
    public List<VisitRecord> getAll() {
        return service.getAllVisits();
    }

    @GetMapping("/customer/{customerId}")
    public List<VisitRecord> getByCustomer(
            @PathVariable Long customerId) {
        return service.getVisitsByCustomer(customerId);
    }
}
