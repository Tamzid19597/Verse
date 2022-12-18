package com.example.easyrent.Service;

import com.example.easyrent.Repository.SingleServiceRepository;
import com.example.easyrent.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private SingleServiceRepository singleServiceRepository;
    CourseService courseService;
    @BeforeEach
    void setUp()
    {
        this.courseService
                = new CourseService(this.singleServiceRepository);
    }
    @Test
    void getAllCourse()
    {
        courseService.getAllCourse();
        verify(singleServiceRepository).findAll();
    }
}
