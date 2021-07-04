package com.manning.sbip.ch07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class CourseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseTrackerApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${app.description}") String appDescription,
			@Value("${app.version}") String appVersion) {

		return new OpenAPI().info(new Info().title("Course Tracker API").version(appVersion)
				.description(appDescription).termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}

}
