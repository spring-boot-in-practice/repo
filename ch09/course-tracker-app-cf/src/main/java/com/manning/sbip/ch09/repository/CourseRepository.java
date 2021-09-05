package com.manning.sbip.ch09.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch09.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
