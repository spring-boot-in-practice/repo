package com.manning.sbip.ch08.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.manning.sbip.ch08.api.WebClientApi;
import com.manning.sbip.ch08.model.Course;

@Configuration
public class ApiClient {

	@Bean
	public CommandLineRunner invokeCourseTrackerApi(WebClientApi webClientApi) {
		return args -> {
			Course course = Course.builder().name("Angular Basics").category("JavaScript").rating(3)
					.description("Learn Angular Fundamentals").build();

			webClientApi.postNewCourse(course)
			.thenMany(webClientApi.getAllCourses())
			.subscribe();
		};
	}
}
