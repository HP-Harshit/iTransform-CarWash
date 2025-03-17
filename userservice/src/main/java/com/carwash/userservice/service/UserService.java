package com.carwash.userservice.service;

import com.carwash.userservice.entity.User;
import com.carwash.userservice.repository.UserRepository;
import com.carwash.userservice.dto.SignupRequest;
import com.carwash.userservice.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to register a new user
    public User registerUser(SignupRequest signupRequest) {
        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(signupRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email already exists!");
        }

        // Create a new user and encode the password
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole("CUSTOMER"); // Default role

        return userRepository.save(user);
    }

    // Method to authenticate a user
    public User authenticateUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }
        return user.get();
    }

    // Method to fetch user profile by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
