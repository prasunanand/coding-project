package com.example.calApp.repositories;

import com.example.calApp.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByUserId(Long userId);
    List<Availability> findByUserIdAndDayOfWeek(Long userId, String dayOfWeek);
}