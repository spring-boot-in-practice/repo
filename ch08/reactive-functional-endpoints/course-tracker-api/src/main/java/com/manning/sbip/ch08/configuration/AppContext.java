package com.manning.sbip.ch08.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.manning.sbip.ch08.model.Course;
import com.manning.sbip.ch08.repository.CourseRepository;

import reactor.core.publisher.Flux;

@Configuration
public class AppContext {

	@Bean
	public CommandLineRunner init(CourseRepository courseRepository) {
		return args -> {

			Course course1 = Course.builder().name("Mastering Spring Boot").category("Spring").rating(4)
					.description("Mastering Spring Boot").build();
			Course course2 = Course.builder().name("Mastering Python").category("Python").rating(5)
					.description("Mastering Python").build();
			Course course3 = Course.builder().name("Mastering Go").category("Go").rating(3).description("Mastering Go")
					.build();

			Flux.just(course1, course2, course3).flatMap(courseRepository::save).thenMany(courseRepository.findAll())
					.subscribe(System.out::println);
		};
	}
}
