package com.example.easyrent.Service;

import com.example.easyrent.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserRepository userRepository;
    UserService userService;
    @BeforeEach
    void setUp()
    {
        this.userService
                = new UserService(this.userRepository);
    }
    @Test
    void getAllPerson()
    {
        userService.getAllUser();
        verify(userRepository).findAll();
    }
}
