package com.manning.sbip.ch07.service;

import java.util.Optional;

import com.manning.sbip.ch07.model.Course;

public interface CourseService {

	Course createCourse(Course course);
	
	Optional<Course> getCourseById(long courseId);
	
	Iterable<Course> getCoursesByCategory(String category);
	
	Iterable<Course> getCourses();
	
	void updateCourse(Long courseId, Course course);
	
	void deleteCourseById(long courseId);

	void deleteCourses();
}
