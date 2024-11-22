package com.example.calApp.entities.jdbcConverter;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    private Long userId;  // User who booked the event

    private Long availabilityId;  // The availability slot for the event

}
