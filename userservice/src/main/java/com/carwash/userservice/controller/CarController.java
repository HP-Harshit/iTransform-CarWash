package com.carwash.userservice.controller;

import com.carwash.userservice.entity.Car;
import com.carwash.userservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Add a new car
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car, @RequestParam Long userId) {
        return ResponseEntity.ok(carService.addCar(car, userId));
    }

    // Get all cars for a user
    @GetMapping
    public ResponseEntity<List<Car>> getCarsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(carService.getCarsByUserId(userId));
    }

    // Update a car
    @PutMapping("/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Long carId, @RequestBody Car updatedCar, @RequestParam Long userId) {
        return ResponseEntity.ok(carService.updateCar(carId, updatedCar, userId));
    }

    // Delete a car
    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId, @RequestParam Long userId) {
        carService.deleteCar(carId, userId);
        return ResponseEntity.ok("Car deleted successfully");
    }
}
