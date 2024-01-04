package com.example.user.userController;

import com.example.security.auth.AuthenticationController;
import com.example.security.auth.AuthenticationRequest;
import com.example.security.auth.AuthenticationResponse;
import com.example.security.auth.RegisterRequest;
import com.example.user.userEntities.LoginRequest;
import com.example.user.userEntities.Role;
import com.example.user.userEntities.UserRegistrationRequest;
import com.example.user.userRepository.UserRepository;
import com.example.user.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.security.auth", "com.example.notes"})
public class UserController {

    private final UserService userService;
    private final AuthenticationController authenticationController;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Creates a new user account
     *
     * @return
     */
    @PostMapping(value = "/auth/signup")
    public ResponseEntity<AuthenticationResponse> userSignUp(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        RegisterRequest registerRequest = RegisterRequest.builder()
                    .userName(userRegistrationRequest.getUserName())
                    .email(userRegistrationRequest.getEmail())
                    .password(userRegistrationRequest.getPassword())
                    .firstname(userRegistrationRequest.getFirstName())
                    .lastname(userRegistrationRequest.getLastName())
                    .role(Role.USER)
                    .build();
        return authenticationController.register(registerRequest);
    }

    /**

        Receive authentication token when an existing user
        logs into the account

     */
    @PostMapping(value = "/auth/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody LoginRequest loginRequest) {

        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .username(loginRequest.getUserName())
                .password(loginRequest.getPassword())
                .build();

        return authenticationController.authenticate(authenticationRequest);
    }
}
