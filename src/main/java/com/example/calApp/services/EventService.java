package com.example.calApp.services;

import com.example.calApp.entities.Availability;
import com.example.calApp.entities.UserEntity;
import com.example.calApp.entities.jdbcConverter.Event;
import com.example.calApp.exceptions.SlotAlreadyBookedException;
import com.example.calApp.repositories.AvailabilityRepository;
import com.example.calApp.repositories.EventRepository;
import com.example.calApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    // Create an event for a user
    public Event createEvent(Long userId, Long availabilityId, String title, LocalDate eventDate) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // TODO: check day
        Availability availability = availabilityRepository.findById(availabilityId).orElseThrow(() -> new RuntimeException("Availability not found"));

        LocalDateTime eventStartTime = combineDateWithTime(eventDate, availability.getStartTime());
        LocalDateTime eventEndTime = combineDateWithTime(eventDate, availability.getEndTime());

        // validate if day is right
        DayOfWeek dayOfWeek = eventStartTime.getDayOfWeek();
        if(!availability.getDayOfWeek().equals(dayOfWeek.toString())){
            throw new RuntimeException("Invalid day of week "+dayOfWeek.toString());
        }

        if (eventStartTime.isBefore(eventEndTime) && !isSlotAvailable(userId, eventStartTime, eventEndTime)) {
            throw new SlotAlreadyBookedException("The time slot is already booked.");
        }
        // Check if the user is available for the time slot (i.e., no overlap)

        Event event = new Event();
        event.setTitle(title);
        event.setStartTime(eventStartTime);
        event.setEndTime(eventEndTime);
        event.setUserId(userId);
        event.setAvailabilityId(availability.getId());
        return eventRepository.save(event);

    }

    private LocalDateTime combineDateWithTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }


    public List<Event> getEventsForUser(Long userId) {
        return eventRepository.findByUserId(userId);
    }

    public boolean isSlotAvailable(Long userId, LocalDateTime start, LocalDateTime end) {
        List<Event> overlappingEvents = eventRepository.findByUserIdAndStartTimeBetween(userId, start, end);
        return overlappingEvents.isEmpty();  // No existing event conflicts
    }



}
