package com.carwash.carservice.controller;

import com.carwash.carservice.model.Car;
import com.carwash.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Add a new car
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        System.out.println("Adding car: " + car); // Debug log
        validateCar(car); // Validate input
        return ResponseEntity.ok(carService.addCar(car));
    }

    // Get car by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        System.out.println("Fetching car with ID: " + id); // Debug log
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get cars by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable String userId) {
        System.out.println("Fetching cars for user ID: " + userId); // Debug log
        return ResponseEntity.ok(carService.getCarsByUserId(userId));
    }

    // Update a car
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        System.out.println("Updating car with ID: " + id + " -> " + car); // Debug log
        validateCar(car); // Validate input
        return ResponseEntity.ok(carService.updateCar(id, car));
    }

    // Delete a car
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        System.out.println("Deleting car with ID: " + id); // Debug log
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    // Validate car input
    private void validateCar(Car car) {
        if (car.getMake() == null || car.getModel() == null || car.getYear() == null || car.getUserId() == null) {
            throw new IllegalArgumentException("Car details (make, model, year, userId) must not be null!");
        }
    }
}
