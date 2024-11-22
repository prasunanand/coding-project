package com.example.calApp.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
