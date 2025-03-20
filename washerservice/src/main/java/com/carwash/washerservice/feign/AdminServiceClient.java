package com.carwash.washerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "admin-service")
public interface AdminServiceClient {
    @GetMapping("/api/admin")
    String getAdminDetails();
}
