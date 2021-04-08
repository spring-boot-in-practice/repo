package com.manning.sbip.ch03.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch03.dto.AuthorCourseDto;
import com.manning.sbip.ch03.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	@Query("SELECT new com.manning.sbip.ch03.dto.AuthorCourseDto(c.id, a.name, c.name, c.description) from AUTHOR a, COURSES c, AUTHORS_COURSES ac where a.id = ac.authorId and c.id=ac.courseId and ac.authorId=?1")
	Iterable<AuthorCourseDto> getAuthorCourseInfo(long authorId);
}
