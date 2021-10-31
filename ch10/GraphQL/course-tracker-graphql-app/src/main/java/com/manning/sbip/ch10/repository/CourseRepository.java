package com.manning.sbip.ch10.repository;

import com.manning.sbip.ch10.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, Integer>  {

    Flux<Course> findByCategory(String category);
}
