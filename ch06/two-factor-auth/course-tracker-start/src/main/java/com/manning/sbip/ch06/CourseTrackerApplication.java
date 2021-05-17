package com.manning.sbip.ch06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CourseTrackerApplication {

    @Value("${DB_PASSWORD:NOT_CONFIGURED}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerApplication.class, args);
    }

    @GetMapping("/value")
    public String password() {
        return value;
    }

}