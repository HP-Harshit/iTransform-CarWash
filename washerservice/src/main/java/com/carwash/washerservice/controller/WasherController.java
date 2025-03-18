package com.carwash.washerservice.controller;

import com.carwash.washerservice.dto.WasherDTO;
import com.carwash.washerservice.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/washers")
public class WasherController {

    @Autowired
    private WasherService washerService;

    @GetMapping
    public List<WasherDTO> getAllWashers() {
        return washerService.getAllWashers();
    }

    @GetMapping("/{id}")
    public WasherDTO getWasherById(@PathVariable Long id) {
        return washerService.getWasherById(id);
    }

    @PostMapping
    public WasherDTO addWasher(@RequestBody WasherDTO washerDTO) {
        return washerService.addWasher(washerDTO);
    }

    @PutMapping("/{id}")
    public WasherDTO updateWasher(@PathVariable Long id, @RequestBody WasherDTO washerDTO) {
        return washerService.updateWasher(id, washerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWasher(@PathVariable Long id) {
        washerService.deleteWasher(id);
        return ResponseEntity.ok().build();
    }
}
