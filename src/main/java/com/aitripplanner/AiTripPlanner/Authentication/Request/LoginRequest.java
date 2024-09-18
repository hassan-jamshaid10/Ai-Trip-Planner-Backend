package com.aitripplanner.AiTripPlanner.Authentication.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
