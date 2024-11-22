package com.example.calApp.repositories;

import com.example.calApp.entities.jdbcConverter.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUserId(Long userId);  // Get all events for a user
    List<Event> findByUserIdAndStartTimeBetween(Long userId, LocalDateTime startTime, LocalDateTime endTime);
}
