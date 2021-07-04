package com.manning.sbip.ch07.controller;

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

@RestController
@RequestMapping("/courses/")
public class CourseController {
	
	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public Iterable<Course> getAllCourses() {
		return courseService.getCourses();
	}
	
	@GetMapping("{id}")
	public Course getCourseById(@PathVariable("id") long courseId) {
		return courseService.getCourseById(courseId);
	}
	
	@GetMapping("category/{name}")
	public Iterable<Course> getCourseByCategory(@PathVariable("name") String category) {
		return courseService.getCoursesByCategory(category);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@PutMapping("{id}")
	public Course updateCourse(@PathVariable("id") long courseId, @RequestBody Course course) {
		return courseService.updateCourse(courseId, course);
	}
	
	@DeleteMapping("{id}")
	void deleteCourseById(@PathVariable("id") long courseId) {
		courseService.deleteCourseById(courseId);
	}
	
	@DeleteMapping
	void deleteCourses() {
		courseService.deleteCourses();
	}

}
