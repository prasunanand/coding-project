package com.example.calApp.controllers;

import com.example.calApp.dto.ApiResponse;
import com.example.calApp.dto.AvailabilitySetRequest;
import com.example.calApp.dto.BookEventRequest;
import com.example.calApp.entities.jdbcConverter.Event;
import com.example.calApp.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class EventController {

    @Autowired
    EventService eventService;

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    // Create an event
    @PostMapping("/bookEvent")
    public ResponseEntity<ApiResponse<Event>> bookEvent(@RequestBody BookEventRequest bookEventRequest) {

        logger.info("Request received");

        Event event = eventService.createEvent(bookEventRequest.getUserId(), bookEventRequest.getAvailabilityId(),
                bookEventRequest.getTitle(), bookEventRequest.getEventDate());
        ApiResponse<Event> response = new ApiResponse<>("success", "Event Booked Successfully", event);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get events for a user
    @GetMapping("/{userId}/events")
    public ResponseEntity<ApiResponse<List<Event>>> getEventsForUser(@PathVariable Long userId) {
        List<Event> events = eventService.getEventsForUser(userId);
        ApiResponse<List<Event>> response = new ApiResponse<>("success", "Events Fetched Successfully", events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
