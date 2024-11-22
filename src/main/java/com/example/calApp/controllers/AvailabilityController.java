package com.example.calApp.controllers;

import com.example.calApp.dto.ApiResponse;
import com.example.calApp.dto.AvailabilitySetRequest;
import com.example.calApp.entities.Availability;
import com.example.calApp.dto.OverlapRequest;
import com.example.calApp.services.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/{userId}/availability")
    public ResponseEntity<ApiResponse<AvailabilitySetRequest>> setAvailability(@PathVariable Long userId, @RequestBody AvailabilitySetRequest availabilitySetRequest) {
        // Store the user's availability in the database
        availabilityService.setAvailability(userId, availabilitySetRequest.getAvailability());
        ApiResponse<AvailabilitySetRequest> response = new ApiResponse<>("success", "Availability Created Successfully", availabilitySetRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/availability")
    public ResponseEntity<ApiResponse<List<Availability>>> getAvailability(@PathVariable Long userId) {
        // Retrieve and return the user's availability from the database
        List<Availability> availabilities = availabilityService.getAvailability(userId);
        ApiResponse<List<Availability>> response = new ApiResponse<>("success", "Availability Fetched Successfully", availabilities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/overlap")
    public ResponseEntity<ApiResponse<List<Availability>>> checkOverlap(@RequestBody OverlapRequest request) {
        // Retrieve the availability of both users from the database
        List<Availability> overlap = availabilityService.findOverlap(request.getUser1Id(), request.getUser2Id());
        ApiResponse<List<Availability>> response = new ApiResponse<>("success", "Overlaps found", overlap);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}