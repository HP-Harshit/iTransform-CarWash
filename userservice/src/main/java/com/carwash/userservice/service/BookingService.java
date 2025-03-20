package com.carwash.userservice.service;

import com.carwash.userservice.entity.Booking;
import com.carwash.userservice.entity.Car;
import com.carwash.userservice.entity.User;
import com.carwash.userservice.repository.BookingRepository;
import com.carwash.userservice.repository.CarRepository;
import com.carwash.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking bookNow(Long userId, Long carId, String packageDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setPackageDetails(packageDetails);
        booking.setStatus("PENDING");
        booking.setScheduledTime(LocalDateTime.now()); // Immediate booking
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking cancelBooking(Long bookingId, Long userId) {
        // Fetch booking by ID
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Check if the booking belongs to the user
        if (!booking.getUser().getId().equals(userId)) {
            throw new RuntimeException("Booking does not belong to the user");
        }

        // Update the booking status to "CANCELED"
        booking.setStatus("CANCELED");
        return bookingRepository.save(booking); // Save the updated booking
    }
}
