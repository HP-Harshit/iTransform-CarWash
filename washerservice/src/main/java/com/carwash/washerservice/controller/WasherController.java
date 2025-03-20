package com.carwash.washerservice.controller;

import com.carwash.washerservice.service.AdminServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/washers")
public class WasherController {

    @Autowired
    private AdminServiceProxy adminServiceProxy;

    @GetMapping
    public String test() {
        return "Hello from Washer-Service";
    }

    @GetMapping("/admin")
    public String getAdminDetails() {
        return adminServiceProxy.fetchAdminDetails();
    }
}
