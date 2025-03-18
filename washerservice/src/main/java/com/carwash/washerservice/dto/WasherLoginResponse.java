package com.carwash.washerservice.dto;

import com.carwash.washerservice.entity.Washer;
import lombok.Data;

@Data
public class WasherLoginResponse {
    private String token;
    private Washer washer;

    public WasherLoginResponse(String token, Washer washer) {
        this.token = token;
        this.washer = washer;
    }
}
