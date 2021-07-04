package com.manning.sbip.ch07.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manning.sbip.ch07.exception.CourseNotFoundException;
import com.manning.sbip.ch07.model.Course;
import com.manning.sbip.ch07.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course getCourseById(long courseId) {
		return courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("No course with id %s is available" + courseId));
	}

	@Override
	public Iterable<Course> getCoursesByCategory(String category) {
		return courseRepository.findAllByCategory(category);
	}

	@Override
	public Iterable<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course updateCourse(long courseId, Course course) {
		Course existingCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("No course with id %s is available" + courseId));
		existingCourse.setName(course.getName());
		existingCourse.setCategory(course.getCategory());
		existingCourse.setDescription(course.getDescription());
		existingCourse.setRating(course.getRating());
		return courseRepository.save(existingCourse);
	}

	@Override
	public void deleteCourses() {
		courseRepository.deleteAll();
	}

	@Override
	public void deleteCourseById(long courseId) {
		courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("No course with id %s is available" + courseId));
		courseRepository.deleteById(courseId);
	}

}
