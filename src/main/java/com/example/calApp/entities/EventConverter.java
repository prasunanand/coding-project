package com.example.calApp.entities;

import com.example.calApp.entities.jdbcConverter.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class EventConverter implements AttributeConverter<List<Event>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Event> event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting Event list to JSON", e);
        }
    }

    @Override
    public List<Event> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, Event.class));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error converting JSON to Event list", e);
        }
    }
}
