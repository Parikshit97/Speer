package com.example.user.userService;

import com.example.user.userEntities.LoginRequest;
import com.example.user.userEntities.User;
import com.example.user.userEntities.UserRegistrationRequest;
import com.example.user.userRepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationRequest userRegistrationRequest){
        User user = User.builder()
                        .firstName(userRegistrationRequest.getFirstName())
                        .lastName(userRegistrationRequest.getLastName())
                        .email(userRegistrationRequest.getEmail())
                        .password(userRegistrationRequest.getPassword())
                        .userName(userRegistrationRequest.getUserName())
                        .build();
        log.info("User Registration Request {}", userRegistrationRequest);
        userRepository.save(user);
        log.info("User Registration successful");
        return user;
    }

    public void loginUser(LoginRequest loginRequest){

    }


}
