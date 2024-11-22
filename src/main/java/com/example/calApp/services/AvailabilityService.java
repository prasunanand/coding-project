package com.example.calApp.services;

import com.example.calApp.entities.Availability;
import com.example.calApp.entities.UserEntity;
import com.example.calApp.exceptions.UserNotFoundException;
import com.example.calApp.repositories.AvailabilityRepository;
import com.example.calApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    @Autowired
    AvailabilityRepository availabilityRepository;

    @Autowired
    UserRepository userRepository;

    public List<Availability> getAvailability(Long userId){
        List<Availability> availabilities = availabilityRepository.findByUserId(userId);
        if (availabilities.isEmpty()){
            throw new Error();
        }
        return availabilities;
    }

    public void setAvailability(Long userId, List<Availability> availabilityList) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        //validation

        availabilityList.forEach(availability -> availability.setUserId(userId));
        availabilityRepository.saveAll(availabilityList);
        List<Long> availabilityIds = user.getAvailability();
        availabilityList.forEach(availability -> availabilityIds.add(availability.getId()));
        user.setAvailability(availabilityIds);
        userRepository.save(user);
    }

    public List<Availability> findOverlap(Long userId1, Long userId2) {
        List<Availability> user1Availability = availabilityRepository.findByUserId(userId1);
        List<Availability> user2Availability = availabilityRepository.findByUserId(userId2);

        // Find overlap
        return user1Availability.stream()
                .filter(user1Slot -> user2Availability.stream().anyMatch(user2Slot ->
                        user1Slot.getDayOfWeek().equals(user2Slot.getDayOfWeek()) &&
                        user1Slot.getStartTime().isBefore(user2Slot.getEndTime()) &&
                        user1Slot.getEndTime().isAfter(user2Slot.getStartTime())))
                .toList();
    }
}