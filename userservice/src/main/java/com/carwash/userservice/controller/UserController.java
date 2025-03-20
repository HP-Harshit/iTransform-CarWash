package com.carwash.userservice.controller;

import com.carwash.userservice.service.WasherServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private WasherServiceProxy washerServiceProxy;

    @GetMapping
    public String test() {
        return "Hello from User-Service";
    }

    @GetMapping("/washers")
    public String getWasherDetails() {
        return washerServiceProxy.fetchWasherDetails();
    }
}
