package com.manning.sbip.ch06.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch06.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
}
