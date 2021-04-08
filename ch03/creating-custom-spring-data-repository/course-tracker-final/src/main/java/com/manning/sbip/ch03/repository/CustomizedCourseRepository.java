package com.manning.sbip.ch03.repository;

import com.manning.sbip.ch03.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long> {
}
