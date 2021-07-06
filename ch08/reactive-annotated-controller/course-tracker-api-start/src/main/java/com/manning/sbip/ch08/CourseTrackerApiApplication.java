package com.manning.sbip.ch08;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.manning.sbip.ch08.model.Course;
import com.manning.sbip.ch08.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class CourseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseTrackerApiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(CourseRepository courseRepository) {
		return args -> {
			
			Course course1 = Course.builder().name("Mastering Spring Boot").category("Spring").rating(4).description("Mastering Spring Boot").build();
			Course course2 = Course.builder().name("Mastering Python").category("Python").rating(5).description("Mastering Python").build();
			Course course3 = Course.builder().name("Mastering Go").category("Go").rating(3).description("Mastering Go").build();
			
			Flux.just(course1, course2, course3)
			.flatMap(courseRepository::save)
			.thenMany(courseRepository.findAll())
			.subscribe();
		};
	}

}
