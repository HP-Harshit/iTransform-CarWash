package com.carwash.carservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Long id; // Car ID (optional, mostly for updates)
    private String make; // Car manufacturer, e.g., "Toyota"
    private String model; // Car model, e.g., "Camry"
    private String year; // Manufacturing year, e.g., "2023"
    private String imageUrl; // URL of the car's image
    private String userId; // Associated User ID
}
