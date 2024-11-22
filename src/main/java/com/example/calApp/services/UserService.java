package com.example.calApp.services;

import com.example.calApp.entities.UserEntity;
import com.example.calApp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    UserRepository userRepository;


    public int create(String name, String email){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setName(name);
        userEntity.setAvailability(new ArrayList<Long>());
        userEntity = userRepository.save(userEntity);
        logger.info("user created: {}", userEntity);


        return 1;
    }
}
