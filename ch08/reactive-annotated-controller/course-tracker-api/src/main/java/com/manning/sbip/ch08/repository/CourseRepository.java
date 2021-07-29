package com.manning.sbip.ch08.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch08.model.Course;

import reactor.core.publisher.Flux;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
	
	Flux<Course> findAllByCategory(String category);
}
