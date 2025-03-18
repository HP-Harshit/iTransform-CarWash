package com.carwash.washerservice.service;

import com.carwash.washerservice.dto.WasherDTO;
import com.carwash.washerservice.dto.WasherLoginRequest;
import com.carwash.washerservice.dto.WasherSignupRequest;
import com.carwash.washerservice.entity.Washer;
import com.carwash.washerservice.repository.WasherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WasherService {

    @Autowired
    private WasherRepository washerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Washer registerWasher(WasherSignupRequest washerSignupRequest) {
        Optional<Washer> existingWasher = washerRepository.findByEmail(washerSignupRequest.getEmail());
        if(existingWasher.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email already exists!");
        }

            Washer washer = new Washer();
            washer.setFirstName(washerSignupRequest.getFirstName());
            washer.setLastName(washerSignupRequest.getLastName());
            washer.setEmail(washerSignupRequest.getEmail());
            washer.setPassword(passwordEncoder.encode(washerSignupRequest.getPassword()));
            washer.setRole("CUSTOMER"); // Default role

            return washerRepository.save(washer);
    }

    public Washer authenticateWasher(WasherLoginRequest washerLoginRequest) {
        Optional<Washer> user = washerRepository.findByEmail(washerLoginRequest.getEmail());
        if (user.isEmpty() || !passwordEncoder.matches(washerLoginRequest.getPassword(), user.get().getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password!");
        }
        return user.get();
    }

    // Method to fetch user profile by ID
    public Washer getUserById(Long id) {
        return washerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id));
    }
}
