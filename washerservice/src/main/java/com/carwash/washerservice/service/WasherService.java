package com.carwash.washerservice.service;

import com.carwash.washerservice.dto.WasherDTO;
import com.carwash.washerservice.entity.Washer;
import com.carwash.washerservice.repository.WasherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasherService {

    @Autowired
    private WasherRepository washerRepository;

    public List<WasherDTO> getAllWashers() {
        return washerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WasherDTO getWasherById(Long id) {
        Washer washer = washerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Washer not found with ID: " + id));
        return convertToDTO(washer);
    }

    public WasherDTO addWasher(WasherDTO washerDTO) {
        Washer washer = convertToEntity(washerDTO);
        Washer savedWasher = washerRepository.save(washer);
        return convertToDTO(savedWasher);
    }

    public WasherDTO updateWasher(Long id, WasherDTO washerDTO) {
        Washer existingWasher = washerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Washer not found with ID: " + id));

        // Update the entity with DTO values
        existingWasher.setName(washerDTO.getName());
        existingWasher.setEmail(washerDTO.getEmail());
        existingWasher.setPhone(washerDTO.getPhone());
        existingWasher.setAvailable(washerDTO.isAvailable());

        Washer updatedWasher = washerRepository.save(existingWasher);
        return convertToDTO(updatedWasher);
    }

    public void deleteWasher(Long id) {
        washerRepository.deleteById(id);
    }

    // Convert entity to DTO
    private WasherDTO convertToDTO(Washer washer) {
        return new WasherDTO(
                washer.getId(),
                washer.getName(),
                washer.getEmail(),
                washer.getPhone(),
                washer.isAvailable()
        );
    }

    // Convert DTO to entity
    private Washer convertToEntity(WasherDTO washerDTO) {
        Washer washer = new Washer();
        washer.setId(washerDTO.getId());
        washer.setName(washerDTO.getName());
        washer.setEmail(washerDTO.getEmail());
        washer.setPhone(washerDTO.getPhone());
        washer.setAvailable(washerDTO.isAvailable());
        return washer;
    }
}
