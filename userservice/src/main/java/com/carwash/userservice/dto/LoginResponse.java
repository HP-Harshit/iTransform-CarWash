package com.carwash.userservice.dto;

import com.carwash.userservice.entity.User;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private User user;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
