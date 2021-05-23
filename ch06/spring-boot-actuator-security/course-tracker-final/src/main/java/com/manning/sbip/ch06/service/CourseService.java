package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.Course;
import com.manning.sbip.ch06.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Iterable<Course> getAvailableCourses() {
        return courseRepository.findAll();
    }
}
