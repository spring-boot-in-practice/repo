package com.manning.sbip.ch07.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/courses/")
@Tag(name = "Course Controller", description = "This REST controller provide services to manage courses in the Course Tracker application")
public class CourseController {
	
	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all courses available in the Course Tracker application")
	public Iterable<Course> getAllCourses() {
		return courseService.getCourses();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides course details for the supplied course id from the Course Tracker application")
	public Optional<Course> getCourseById(@PathVariable("id") long courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@GetMapping("category/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides course details for the supplied course category from the Course Tracker application")
	public Iterable<Course> getCourseByCategory(@PathVariable("name") String category) {
		return courseService.getCoursesByCategory(category);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creates a new course in the Course Tracker application")
	public Course createCourse(@Valid @RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Updates the course details in the Course Tracker application for the supplied course id")
	public void updateCourse(@PathVariable("id") long courseId, @Valid @RequestBody Course course) {
		courseService.updateCourse(courseId, course);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes the course details for the supplied course id from the Course Tracker application")
	void deleteCourseById(@PathVariable("id") long courseId) {
		courseService.deleteCourseById(courseId);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes all courses from the Course Tracker application")
	void deleteCourses() {
		courseService.deleteCourses();
	}

}
