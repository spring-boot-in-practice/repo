package com.manning.sbip.ch04.endpoint;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
@Endpoint(id = "course")
public class CourseEndpoint {

	@Autowired
	private CourseRepository courseRepository;

	@ReadOperation
	public Iterable<Course> courses() {
		return courseRepository.findAll();
	}

	@ReadOperation
	public Course selectCourse(@Selector Long courseId) {
		List<Course> courses = StreamSupport.stream(courseRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		Optional<Course> optionalCourse = courses.stream().filter(course -> courseId == course.getId()).findFirst();
		if(optionalCourse.isPresent()) {
			return optionalCourse.get();
		}
		return null;
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
