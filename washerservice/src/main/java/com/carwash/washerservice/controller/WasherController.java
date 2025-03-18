package com.carwash.washerservice.controller;

import com.carwash.washerservice.dto.WasherLoginRequest;
import com.carwash.washerservice.dto.WasherLoginResponse;
import com.carwash.washerservice.dto.WasherSignupRequest;
import com.carwash.washerservice.entity.Washer;
import com.carwash.washerservice.service.WasherService;
import com.carwash.washerservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/washers")
public class WasherController {

    @Autowired
    private WasherService washerService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<Washer> registerWasher(@RequestBody WasherSignupRequest washerSignupRequest){
        Washer registeredWasher = washerService.registerWasher(washerSignupRequest);
        return new ResponseEntity<>(registeredWasher, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<WasherLoginResponse> loginUser(@RequestBody WasherLoginRequest washerLoginRequest) {
        Washer authenticatedWasher = washerService.authenticateWasher(washerLoginRequest);
        String token = jwtUtil.generateToken(authenticatedWasher.getEmail(), authenticatedWasher.getRole()); // Generate JWT token

        WasherLoginResponse response = new WasherLoginResponse(token, authenticatedWasher);

        return ResponseEntity.ok(response);
    }
}
