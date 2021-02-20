package com.manning.sbip.a02.service;

import com.manning.sbip.a02.model.Course;

import java.util.Optional;

public interface CourseService {

    Iterable<Course> createCourse(Course course);

    Optional<Course> findCourseById(int courseId);

    Iterable<Course> findAllCourses();

    Iterable<Course> updateCourse(Course course);

    Iterable<Course> deleteCourseById(int courseId);

}
