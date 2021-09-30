package com.manning.sbip.ch10.service

import com.manning.sbip.ch10.model.Course
import java.util.*

interface CourseService {
    fun createCourse(course: Course): Course
    fun findCourseById(courseId: Long): Optional<Course>
    fun findAllCourses(): Iterable<Course>
    fun updateCourse(course: Course): Course
    fun deleteCourseById(courseId: Long)
}