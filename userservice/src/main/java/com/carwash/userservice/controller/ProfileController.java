package com.carwash.userservice.controller;

import com.carwash.userservice.entity.User;
import com.carwash.userservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<User> getProfile(@RequestParam Long userId) {
        return ResponseEntity.ok(profileService.getUserProfile(userId));
    }

    @PutMapping
    public ResponseEntity<User> updateProfile(@RequestParam Long userId, @RequestBody User updatedUser) {
        return ResponseEntity.ok(profileService.updateUserProfile(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long userId) {
        profileService.deleteUserProfile(userId);
        return ResponseEntity.ok("User profile deleted successfully");
    }
}
