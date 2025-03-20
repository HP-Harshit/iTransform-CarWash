package com.carwash.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "washer-service")
public interface WasherServiceClient {
    @GetMapping("/api/washers")
    String getWasherDetails();
}
