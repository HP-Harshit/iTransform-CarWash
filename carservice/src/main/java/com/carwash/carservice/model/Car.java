package com.carwash.carservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String make;

    @Setter
    @Getter
    @Column(nullable = false)
    private String model;

    @Setter
    @Getter
    @Column(nullable = false)
    private String year;

    @Setter
    @Getter
    private String imageUrl; // Optional, for storing car image URL

    @Column(nullable = false)
    private String userId; // To associate the car with a specific user


}
