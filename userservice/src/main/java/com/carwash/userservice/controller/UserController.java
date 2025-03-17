package com.carwash.userservice.controller;

import com.carwash.userservice.dto.SignupRequest;
import com.carwash.userservice.dto.LoginResponse;
import com.carwash.userservice.dto.LoginRequest;
import com.carwash.userservice.entity.User;
import com.carwash.userservice.service.UserService;
import com.carwash.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint for user registration
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody SignupRequest signupRequest) {
        User registeredUser = userService.registerUser(signupRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        User authenticatedUser = userService.authenticateUser(loginRequest); // Authenticate user
        String token = jwtUtil.generateToken(authenticatedUser.getEmail(), authenticatedUser.getRole()); // Generate JWT token

        // Create a LoginResponse containing both the token and the authenticated user
        LoginResponse response = new LoginResponse(token, authenticatedUser);

        return ResponseEntity.ok(response);
    }


    // Endpoint for fetching user profile
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
