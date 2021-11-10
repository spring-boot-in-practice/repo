package com.manning.sbip.ch07.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.repository.CourseRepository;

@RestController
@RequestMapping("/courses/")
public class CourseController {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_course:read')")
	public Iterable<Course> getAllCourses(@AuthenticationPrincipal Jwt jwt) {
		String author = jwt.getClaim("user_name");
		return courseRepository.findByAuthor(author);
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('SCOPE_course:read')")
	@PostAuthorize("@getAuthor.apply(returnObject, principal.claims['user_name'])")
	public Optional<Course> getCourseById(@PathVariable("id") long courseId) {
		return courseRepository.findById(courseId);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_course:write')")
	public Course createCourse(@RequestBody String name, @AuthenticationPrincipal Jwt jwt) {
		Course course = new Course(null, name, jwt.getClaim("user_name"));
		return courseRepository.save(course);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('SCOPE_course:write')")
	public Optional<Course> updateCourse(@PathVariable("id") long courseId, @RequestBody String name) {
		courseRepository.update(courseId, name);
		return getCourseById(courseId);
	}
}
