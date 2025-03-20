package com.carwash.adminservice.controller;

import com.carwash.adminservice.service.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserServiceProxy userServiceProxy;

    @GetMapping
    public String hello(){
        return "Hello From Admin-Service";
    }

    @GetMapping("/users")
    public String getAllUsers() {
        return userServiceProxy.fetchAllUsers();
    }
}
