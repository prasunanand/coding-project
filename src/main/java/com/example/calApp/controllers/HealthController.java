package com.example.calApp.controllers;

import com.example.calApp.dto.ApiResponse;
import com.example.calApp.dto.HealthStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    @GetMapping("")
    public ResponseEntity<ApiResponse<HealthStatus>> checkHealth() {

        HealthStatus healthStatus = HealthStatus.builder().status("Up").build();
        ApiResponse<HealthStatus> response = new ApiResponse<>("success", "Server Running", healthStatus);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
