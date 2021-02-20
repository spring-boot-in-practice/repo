package com.manning.sbip.a02.controller;

import com.manning.sbip.a02.model.Course;
import com.manning.sbip.a02.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Course> courseList = (List<Course>) courseService.findAllCourses();
        model.addAttribute("courses", courseList.isEmpty() ? Collections.EMPTY_LIST : courseList);
        return "index";
    }

    @GetMapping("/addcourse")
    public String showAddCourseForm(Course course) {
        return "add-course";
    }

    @PostMapping("/addcourse")
    public String addCourse(@Valid Course course, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-course";
        }
        model.addAttribute("courses", courseService.createCourse(course));
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String showUpdateCourseForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", courseService.findCourseById(id).get());
        return "update-course";
    }

    @PutMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") int id, @Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "update-course";
        }
        model.addAttribute("courses", courseService.updateCourse(course));
        return "redirect:/index";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model) {
        model.addAttribute("courses", courseService.deleteCourseById(id));
        return "redirect:/index";
    }
}

