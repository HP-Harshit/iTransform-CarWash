package com.carwash.userservice.controller;

import com.carwash.userservice.entity.Booking;
import com.carwash.userservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/now")
    public ResponseEntity<Booking> bookNow(@RequestParam Long userId, @RequestParam Long carId, @RequestParam String packageDetails) {
        return ResponseEntity.ok(bookingService.bookNow(userId, carId, packageDetails));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getBookingsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId, @RequestParam Long userId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId, userId));
    }
}
