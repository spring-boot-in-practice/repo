package com.manning.sbip.ch04.service;

import com.manning.sbip.ch04.model.Course;

import java.util.Optional;

public interface CourseService {

    Course createCourse(Course course);

    Optional<Course> findCourseById(Long courseId);

    Iterable<Course> findAllCourses();

    Course updateCourse(Course course);

    void deleteCourseById(Long courseId);

    long count();

}
