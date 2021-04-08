package com.manning.sbip.ch03;

import com.manning.sbip.ch03.model.Author;
import com.manning.sbip.ch03.model.Course;
import com.manning.sbip.ch03.repository.AuthorRepository;
import com.manning.sbip.ch03.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenCountAllCoursesThenExpectFiveCourses() {
        assertThat(authorRepository.getAuthorCourseInfo(2)).hasSize(3);
    }

    @Test
    public void givenACourseAvailableWhenGetCourseByNameThenGetCourseDescription() {
        assertThat(courseRepository.getCourseByName("Rapid Spring Boot Application Development"))
                .extracting("description").contains("Spring Boot gives all the power of the Spring Framework without all of the complexity");
    }

}
