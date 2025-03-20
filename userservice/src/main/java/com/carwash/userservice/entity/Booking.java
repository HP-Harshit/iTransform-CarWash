package com.carwash.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private String status; // Status like PENDING, ACCEPTED, COMPLETED

    @Column(nullable = false)
    private String packageDetails;

    @Column(nullable = false)
    private LocalDateTime scheduledTime;
}
