package com.example.calApp.entities;

import com.example.calApp.entities.jdbcConverter.AvailabilityConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@Entity(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT" )
    @Convert(converter = AvailabilityConverter.class)
    private List<Long> availability;
}
