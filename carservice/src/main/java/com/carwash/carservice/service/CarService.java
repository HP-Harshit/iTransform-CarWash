package com.carwash.carservice.service;

import com.carwash.carservice.dto.UserDTO;
import com.carwash.carservice.feign.UserServiceClient;
import com.carwash.carservice.model.Car;
import com.carwash.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    // Add a new car
    public Car addCar(Car car) {
        try {
            System.out.println("Saving car: " + car); // Debug log
            return carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException("Error saving car: " + car, e);
        }
    }

    // Get car by ID
    public Optional<Car> getCarById(Long id) {
        try {
            System.out.println("Fetching car with ID: " + id); // Debug log
            return carRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching car with ID: " + id, e);
        }
    }

    // Get cars by user ID
    public List<Car> getCarsByUserId(String userId) {
        UserDTO user = null;
        try {
            // Fetch user details using Feign client
            System.out.println("Fetching user details for userId: " + userId); // Debug log
            user = userServiceClient.getUserById(userId);
            System.out.println("Fetched user details: " + user.getName());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user details for userId: " + userId, e);
        }

        try {
            System.out.println("Fetching cars for userId: " + userId); // Debug log
            return carRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching cars for userId: " + userId, e);
        }
    }

    // Update a car
    public Car updateCar(Long id, Car updatedCar) {
        try {
            System.out.println("Updating car with ID: " + id + " -> " + updatedCar); // Debug log
            return carRepository.findById(id)
                    .map(car -> {
                        car.setMake(updatedCar.getMake());
                        car.setModel(updatedCar.getModel());
                        car.setYear(updatedCar.getYear());
                        car.setImageUrl(updatedCar.getImageUrl());
                        return carRepository.save(car);
                    }).orElseThrow(() -> new RuntimeException("Car not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error updating car with ID: " + id, e);
        }
    }

    // Delete a car
    public void deleteCar(Long id) {
        try {
            System.out.println("Deleting car with ID: " + id); // Debug log
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting car with ID: " + id, e);
        }
    }
}
