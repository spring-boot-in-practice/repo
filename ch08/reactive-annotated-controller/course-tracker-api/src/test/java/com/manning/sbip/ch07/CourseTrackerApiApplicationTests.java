package com.manning.sbip.ch07;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.manning.sbip.ch08.controller.CourseController;
import com.manning.sbip.ch08.model.Course;
import com.manning.sbip.ch08.repository.CourseRepository;

@SpringBootTest
class CourseTrackerApiApplicationTests {

	private WebTestClient webTestClient;

	@Autowired
	private CourseRepository courseRepository;

	private List<Course> expectedCourses;

	@BeforeEach
	void beforeEach() {
		this.webTestClient = WebTestClient.bindToController(new CourseController(courseRepository)).configureClient()
				.baseUrl("/courses/").build();
		this.expectedCourses = courseRepository.findAll().collectList().block();
	}

	@Test
	void testGetAllCourses() {
		this.webTestClient.get()
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Course.class).isEqualTo(expectedCourses);
	}

	@Test
	void testInvalidCoursesId() {
		this.webTestClient.get().uri("/123").exchange().expectStatus().isNotFound();
	}

	@Test
	void testValidCoursesId() {
		Course course1 = expectedCourses.get(0);
		this.webTestClient.get().uri("/{id}", course1.getId()).exchange().expectStatus().isOk().expectBody(Course.class)
				.isEqualTo(course1);
	}

}
