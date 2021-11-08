package com.manning.sbip.ch07.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.service.CourseService;

@RestController
@RequestMapping("/courses/v1")
public class LegacyCourseController {
	
	private CourseService courseService;
	
	@Autowired
	public LegacyCourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<Course> getAllCourses() {
		return courseService.getCourses();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course createCourse(@Valid @RequestBody Course course) {
		return courseService.createCourse(course);
	}
}
