package com.manning.sbip.ch07.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch07.model.ModernCourse;

@Repository
public interface ModernCourseRepository extends CrudRepository<ModernCourse, Long> {

}
