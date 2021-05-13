package com.manning.sbip.ch06.service;

import java.util.Optional;

import com.manning.sbip.ch06.model.Course;

public interface CourseService {

    Course createCourse(Course course);

    Optional<Course> findCourseById(int courseId);

    Iterable<Course> findAllCourses();

    Course updateCourse(Course course);

    void deleteCourseById(int courseId);

}
