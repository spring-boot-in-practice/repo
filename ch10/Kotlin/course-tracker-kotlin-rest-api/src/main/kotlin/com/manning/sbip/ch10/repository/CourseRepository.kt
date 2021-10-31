package com.manning.sbip.ch10.repository

import com.manning.sbip.ch10.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Long>