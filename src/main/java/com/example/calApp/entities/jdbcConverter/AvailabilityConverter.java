package com.example.calApp.entities.jdbcConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class AvailabilityConverter implements AttributeConverter<List<Long>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> availability) {
        try {
            return objectMapper.writeValueAsString(availability);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting Availability list to JSON", e);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, Long.class));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting JSON to Availability list", e);
        }
    }
}

