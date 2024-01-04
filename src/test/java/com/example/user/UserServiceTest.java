package com.example.user;


import com.example.user.userEntities.User;
import com.example.user.userEntities.UserRegistrationRequest;
import com.example.user.userRepository.UserRepository;
import com.example.user.userService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_SuccessfulRegistration() {
        // given
        UserRegistrationRequest registrationRequest = createUserRegistrationRequest();
        User expectedUser = createUser(registrationRequest);

        // when
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        User registeredUser = userService.registerUser(registrationRequest);

        // then
        verify(userRepository, times(1)).save(any(User.class));
        assertUserFields(registrationRequest, registeredUser);
    }

    private UserRegistrationRequest createUserRegistrationRequest() {
        return new UserRegistrationRequest("John", "Doe", "john.doe@example.com", "password123", "johndoe");
    }

    private User createUser(UserRegistrationRequest registrationRequest) {
        return User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .userName(registrationRequest.getUserName())
                .build();
    }

    private void assertUserFields(UserRegistrationRequest registrationRequest, User user) {
        Assertions.assertEquals(registrationRequest.getFirstName(), user.getFirstName());
        Assertions.assertEquals(registrationRequest.getLastName(), user.getLastName());
        Assertions.assertEquals(registrationRequest.getEmail(), user.getEmail());
        Assertions.assertEquals(registrationRequest.getPassword(), user.getPassword());
        Assertions.assertEquals(registrationRequest.getUserName(), user.getUsername());
    }
}
