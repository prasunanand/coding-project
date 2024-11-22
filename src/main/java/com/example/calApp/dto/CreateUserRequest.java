package com.example.calApp.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String name;
}
