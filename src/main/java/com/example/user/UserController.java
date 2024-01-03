package com.example.user;

import com.example.security.auth.AuthenticationController;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.security.auth"})
public class UserController {

    private final UserService userService;
    private final AuthenticationController authenticationController;
    private final UserRepository userRepository;

    /**
        Creates a new user account
    */
    @PostMapping(value = "/auth/signup")
    public void userSignUp(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        userService.registerUser(userRegistrationRequest);
    }

    /**

        Receive authentication token when an existing user
        logs into the account

     */
    @PostMapping(value = "/auth/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody LoginRequest loginRequest) {

        User userLoginRequest = userRepository.findByUserName(loginRequest.getUserName())
                                              .orElse(null);

        RegisterRequest registerRequest = RegisterRequest.builder()
                    .userName(userLoginRequest.getUsername())
                    .email(userLoginRequest.getEmail())
                    .password(userLoginRequest.getPassword())
                    .firstname(userLoginRequest.getFirstName())
                    .lastname(userLoginRequest.getLastName())
                    .build();

        return authenticationController.register(registerRequest);
    }
}
