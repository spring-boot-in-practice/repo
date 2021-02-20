package com.manning.sbip.a02.service;

import com.manning.sbip.a02.model.Course;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DefaultCourseService implements CourseService {

    private Map<Integer, Course> courses;
    private AtomicInteger courseIdGenerator;

    public DefaultCourseService() {
        this.courses = new HashMap<>();
        this.courseIdGenerator = new AtomicInteger(0);
        initializeCourses();
    }

    @Override
    public Iterable<Course> createCourse(Course course) {
        int courseId = course.getId();
        if(courseId == 0){
            courseId = getCourseId();
            course.setId(courseId);
        }else {
            courseId = course.getId();
        }
        courses.put(courseId, course);
        return findAllCourses();
    }

    @Override
    public Optional<Course> findCourseById(int courseId) {
        return Optional.of(courses.get(courseId));
    }

    @Override
    public List<Course> findAllCourses() {
        List<Course> courseList = new ArrayList<>();
        for(Map.Entry<Integer, Course> courseSet : courses.entrySet()) {
           courseList.add(courseSet.getValue());
        }
        return courseList;
    }

    @Override
    public Iterable<Course> updateCourse(Course course) {
        return createCourse(course);
    }

    @Override
    public Iterable<Course> deleteCourseById(int courseId) {
        courses.remove(courseId);
        return findAllCourses();
    }

    private void initializeCourses() {
        Course rapidSpringBootCourse = new Course(getCourseId(), "Rapid Spring Boot Application Development", "Spring", 4, "Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course springSecurityDslCourse = new Course(getCourseId(), "Getting Started with Spring Security DSL", "Spring", 2, "Learn Spring Security DSL in easy steps");
        Course springCloudKubernetesCourse = new Course(getCourseId(), "Getting Started with Spring Cloud Kubernetes", "Spring", 4, "Master Spring Boot application deployment with Kubernetes");
        courses.put(rapidSpringBootCourse.getId(), rapidSpringBootCourse);
        courses.put(springSecurityDslCourse.getId(), springSecurityDslCourse);
        courses.put(springCloudKubernetesCourse.getId(), springCloudKubernetesCourse);
    }

    private int getCourseId() {
        return courseIdGenerator.incrementAndGet();
    }
}
