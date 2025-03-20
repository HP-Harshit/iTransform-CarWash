package com.carwash.washerservice.service;

import com.carwash.washerservice.feign.AdminServiceClient;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceProxy {
    private final AdminServiceClient adminServiceClient;

    public AdminServiceProxy(AdminServiceClient adminServiceClient) {
        this.adminServiceClient = adminServiceClient;
    }

    public String fetchAdminDetails() {
        return adminServiceClient.getAdminDetails();
    }
}
