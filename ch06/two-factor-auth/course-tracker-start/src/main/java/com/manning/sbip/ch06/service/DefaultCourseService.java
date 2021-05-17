package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.Course;
import com.manning.sbip.ch06.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCourseService implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findCourseById(int courseId) {
        return courseRepository.findById(courseId);
    }

    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(int courseId) {
        courseRepository.deleteById(courseId);
    }
}
