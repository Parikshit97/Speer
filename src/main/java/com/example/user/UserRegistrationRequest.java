package com.example.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
}
