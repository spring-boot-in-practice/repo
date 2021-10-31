package com.manning.sbip.ch10.service

import com.manning.sbip.ch10.exception.CourseNotFoundException
import com.manning.sbip.ch10.model.Course
import com.manning.sbip.ch10.repository.CourseRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CourseHandler (private val courseRepository: CourseRepository) {

    fun createCourse(course: Course): Course =  courseRepository.save(course)

    fun createBulkCourses(courses: List<Course>): Iterable<Course> =  courseRepository.saveAll(courses)

    fun findCourseById(courseId: Long): Course = courseRepository.findById(courseId)
            .orElseThrow { CourseNotFoundException(HttpStatus.NOT_FOUND, "No course with supplied course id was found") }

    fun findAllCourses(): Iterable<Course> =  courseRepository.findAll()

    fun updateCourse(courseId: Long, updatedCourse: Course): Course  {
        return if(courseRepository.existsById(courseId)) {
            courseRepository.save(
                    Course(
                            id = updatedCourse.id,
                            name = updatedCourse.name,
                            category = updatedCourse.category,
                            rating = updatedCourse.rating,
                            description = updatedCourse.description
                    )
            )
        }
        else throw CourseNotFoundException(HttpStatus.NOT_FOUND, "No course with supplied course id was found")
    }


    fun deleteCourseById(courseId: Long) {
        return if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId)
        } else throw CourseNotFoundException(HttpStatus.NOT_FOUND, "No course with supplied course id was found")
    }
}