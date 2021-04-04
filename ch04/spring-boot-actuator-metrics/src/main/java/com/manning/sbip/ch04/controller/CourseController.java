package com.manning.sbip.ch04.controller;

import com.manning.sbip.ch04.model.Course;
import com.manning.sbip.ch04.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private Counter createCourseCounter;
	
	@PostMapping
	public void createCourse(@RequestBody Course course) throws Exception {
		createCourseCounter.increment();
	}
}
