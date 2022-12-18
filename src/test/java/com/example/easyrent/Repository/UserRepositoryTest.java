package com.example.easyrent.Repository;

import com.example.easyrent.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    void isPersonExitsById() {
        User user = new User();
        user.name="Tamzid";
        user.email="test@gmail.com";
        user.password="1234";
        user.role="instructor";
        user.address="abc";
        user.number="01959752029";
        userRepository.save(user);
        Boolean actualResult = userRepository.isPersonExitsByEmail("test@gmail.com");
        assertThat(actualResult).isTrue();
    }
}
