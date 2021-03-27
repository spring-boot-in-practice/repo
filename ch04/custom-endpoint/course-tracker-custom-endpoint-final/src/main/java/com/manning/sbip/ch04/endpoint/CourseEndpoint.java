package com.manning.sbip.ch04.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.manning.sbip.ch04.model.Course;
import com.manning.sbip.ch04.repository.CourseRepository;

@Component
@Endpoint(id = "courses")
//@JmxEndpoint(id = "courses")
public class CourseEndpoint {

	@Autowired
	private CourseRepository courseRepository;

	@ReadOperation
	public Iterable<Course> courses() {
		return courseRepository.findAll();
	}

	@ReadOperation
	public Object selectCourse(@Selector Long courseId) {
		Iterable<Course> courses = courseRepository.findAll();
		for(Course course : courses) {
			if(course.getId().equals(courseId)) {
				return course;
			}
		}
		return String.format("No course with course id %d available", courseId);
	}
	
	@WriteOperation
	public void updateCourseRating(@Selector Long courseId, int newRating) {
		Optional<Course> optionalCourse = courseRepository.findById(courseId);
		if(!optionalCourse.isEmpty()) {
			Course course = optionalCourse.get();
			course.setRating(newRating);
			courseRepository.save(course);
		}
	}
	
	@DeleteOperation
	public void deleteCourses() {
		courseRepository.deleteAll();
	}
	
	@DeleteOperation
	public void deleteCoursebyId(@Selector Long courseId) {
		courseRepository.deleteById(courseId);
	}
}
