package com.example.calApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookEventRequest {
    String title;
    Long userId;
    Long availabilityId;
    LocalDate eventDate;
}
