package com.example.demo.controller;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final CustomerProfileService customerProfileService;
    private final JwtUtil jwtUtil;
    public AuthController(CustomerProfileService customerProfileService, JwtUtil jwtUtil) {
        this.customerProfileService = customerProfileService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        CustomerProfile customer = new CustomerProfile(
            UUID.randomUUID().toString(),
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            "BRONZE",
            true,
            null
        );   
        CustomerProfile savedCustomer = customerProfileService.createCustomerProfile(customer);
        return ResponseEntity.ok(new ApiResponse(true, "Customer registered successfully", savedCustomer));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        CustomerProfile customer = customerProfileService.getCustomerProfileByCustomerId(request.getEmail());
        String token = jwtUtil.generateToken(customer.getEmail(), "USER");
        return ResponseEntity.ok(new ApiResponse(true, "Login successful", token));
    }
}