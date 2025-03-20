package com.carwash.adminservice.service;

import com.carwash.adminservice.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceProxy {

    @Autowired
    private UserServiceClient userServiceClient;

    public String fetchAllUsers() {
        return userServiceClient.getAllUsers();
    }
}
