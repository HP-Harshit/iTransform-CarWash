package com.carwash.userservice.service;

import com.carwash.userservice.entity.Car;
import com.carwash.userservice.entity.User;
import com.carwash.userservice.repository.CarRepository;
import com.carwash.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    // Add a new car for a user
    public Car addCar(Car car, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        car.setUser(user);
        return carRepository.save(car);
    }

    // Get all cars for a user
    public List<Car> getCarsByUserId(Long userId) {
        return carRepository.findByUserId(userId);
    }

    // Update an existing car
    public Car updateCar(Long carId, Car updatedCar, Long userId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // Ensure the car belongs to the user
        if (!car.getUser().getId().equals(userId)) {
            throw new RuntimeException("Car does not belong to the user");
        }

        // Update car details
        car.setModel(updatedCar.getModel());
        car.setPlateNumber(updatedCar.getPlateNumber());
        car.setImage(updatedCar.getImage());

        return carRepository.save(car); // Save updated car
    }

    // Delete a car
    public void deleteCar(Long carId, Long userId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // Ensure the car belongs to the user
        if (!car.getUser().getId().equals(userId)) {
            throw new RuntimeException("Car does not belong to the user");
        }

        carRepository.delete(car); // Delete the car
    }
}
