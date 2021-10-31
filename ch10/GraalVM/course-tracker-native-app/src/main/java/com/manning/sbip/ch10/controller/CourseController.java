package com.manning.sbip.ch10.controller;

import com.manning.sbip.ch10.model.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public Iterable<Course> courses() {
        return List.of(
                Course.builder().id(1).name("Spring Boot").category("Spring").rating(4).description("Spring Boot").build(),
                Course.builder().id(2).name("Spring Native").category("Spring").rating(5).description("Spring Native").build()
        );
    }
}
