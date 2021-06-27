package com.manning.sbip.ch07;

import java.util.Optional;
import java.util.function.BiFunction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.repository.CourseRepository;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CourseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseTrackerApiApplication.class, args);
	}
	
	@Bean
	BiFunction<Optional<Course>, String, Boolean> getAuthor() {
		return (course, userId) -> course.filter(c -> c.getAuthor().equals(userId)).isPresent();
	}
	
	@Bean
	CommandLineRunner createCourse(CourseRepository courseRepository) {
		return (args) -> {
			Course spring = new Course(null, "Spring", "john");
			Course python = new Course(null, "Python", "steve");
			courseRepository.save(spring);
			courseRepository.save(python);
		};
	}
}
