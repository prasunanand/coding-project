package com.example.calApp.controllers;

import com.example.calApp.dto.ApiResponse;
import com.example.calApp.dto.CreateUserRequest;
import com.example.calApp.entities.Availability;
import com.example.calApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<CreateUserRequest>> setAvailability(@RequestBody CreateUserRequest createUserRequest) {
        // Store the user's availability in the database

        userService.create(createUserRequest.getName(), createUserRequest.getEmail());

        ApiResponse<CreateUserRequest> response = new ApiResponse<>("success", "User Created Successfully", createUserRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
