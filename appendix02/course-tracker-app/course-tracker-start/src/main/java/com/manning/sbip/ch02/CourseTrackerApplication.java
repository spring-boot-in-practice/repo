package com.manning.sbip.ch02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CourseTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerApplication.class, args);
    }


}