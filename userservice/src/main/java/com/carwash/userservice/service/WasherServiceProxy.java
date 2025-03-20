package com.carwash.userservice.service;

import com.carwash.userservice.feign.WasherServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasherServiceProxy {

    @Autowired
    private WasherServiceClient washerServiceClient;

    public String fetchWasherDetails() {
        return washerServiceClient.getWasherDetails();
    }
}
