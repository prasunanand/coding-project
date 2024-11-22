package com.example.calApp.dto;

import com.example.calApp.entities.Availability;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilitySetRequest {
    List<Availability> availability;
}
