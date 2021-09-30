package com.manning.sbip.ch10.service

import com.manning.sbip.ch10.model.Course
import com.manning.sbip.ch10.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DefaultCourseService (val courseRepository: CourseRepository) : CourseService {
    override fun createCourse(course: Course): Course {
        return courseRepository.save(course)
    }

    override fun findCourseById(courseId: Long): Optional<Course> {
        return courseRepository.findById(courseId)
    }

    override fun findAllCourses(): Iterable<Course> {
        return courseRepository.findAll()
    }

    override fun updateCourse(course: Course): Course {
        return courseRepository.save(course)
    }

    override fun deleteCourseById(courseId: Long) {
        courseRepository.deleteById(courseId)
    }
}