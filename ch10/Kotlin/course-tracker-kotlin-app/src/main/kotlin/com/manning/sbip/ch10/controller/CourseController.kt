package com.manning.sbip.ch10.controller

import com.manning.sbip.ch10.model.Course
import com.manning.sbip.ch10.service.CourseService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@Controller
class CourseController(private val courseService: CourseService) {

    @GetMapping("/")
    fun index(): String {
        return "redirect:/index"
    }

    @GetMapping("/index")
    fun index(model: Model): String {
        val courseList = courseService.findAllCourses() as List<Course>
        model["courses"] = if (courseList.isEmpty()) Collections.EMPTY_LIST else courseList
        return "index"
    }

    @GetMapping("/addcourse")
    fun showAddCourseForm(course: Course): String {
        return "add-course"
    }

    @PostMapping("/addcourse")
    fun addCourse(course: @Valid Course, result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            return "add-course"
        }
        courseService.createCourse(course)
        model["courses"] = courseService.findAllCourses();
        return "redirect:/index"
    }

    @GetMapping("/update/{id}")
    fun showUpdateCourseForm(@PathVariable("id") id: Long, model: Model): String {
        model["course"] = courseService.findCourseById(id)
        return "update-course"
    }

    @PutMapping("/update/{id}")
    fun updateCourse(@PathVariable("id") id: Long, course: @Valid Course, result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            course.id = id
            return "update-course"
        }
        courseService.updateCourse(id, course)
        model["courses"] = courseService.findAllCourses()
        return "redirect:/index"
    }

    @DeleteMapping("/delete/{id}")
    fun deleteCourse(@PathVariable("id") id: Long, model: Model): String {
        courseService.deleteCourseById(id)
        model["courses"] = courseService.findAllCourses()
        return "redirect:/index"
    }
}