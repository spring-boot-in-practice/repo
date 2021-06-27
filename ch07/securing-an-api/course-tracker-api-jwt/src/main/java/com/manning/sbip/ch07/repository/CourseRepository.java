package com.manning.sbip.ch07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch07.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	List<Course> findByAuthor(String author);

	@Modifying
	@Query("UPDATE Course SET name = :name WHERE id = :id")
	void update(long id, String name);
}
