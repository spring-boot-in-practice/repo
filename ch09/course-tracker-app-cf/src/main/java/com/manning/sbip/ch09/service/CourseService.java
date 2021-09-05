package com.manning.sbip.ch09.service;

import java.util.Optional;

import com.manning.sbip.ch09.model.Course;

public interface CourseService {

    Course createCourse(Course course);

    Optional<Course> findCourseById(Long courseId);

    Iterable<Course> findAllCourses();

    Course updateCourse(Course course);

    void deleteCourseById(Long courseId);

}
