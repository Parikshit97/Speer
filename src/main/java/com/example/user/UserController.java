package com.example.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
        Creates a new user account
    */
    @PostMapping(value = "/auth/signup")
    public void userSignUp(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        userService.registerUser(userRegistrationRequest);
    }
}
