package com.example.user.userEntities;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String userName;
    private String password;
    private String email;
}
