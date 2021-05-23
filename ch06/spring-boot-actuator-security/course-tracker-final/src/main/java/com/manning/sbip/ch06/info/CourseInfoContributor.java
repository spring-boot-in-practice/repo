package com.manning.sbip.ch06.info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.manning.sbip.ch06.model.Course;
import com.manning.sbip.ch06.service.CourseService;

import lombok.Builder;
import lombok.Data;

@Component
public class CourseInfoContributor implements InfoContributor {

    private CourseService courseService;

    @Autowired
    public CourseInfoContributor(CourseService courseService) {
        this.courseService = courseService;
    }

    public void contribute(Info.Builder builder) {
        List<CourseNameRating> courseNameRatingList = new ArrayList<>();
        for(Course course : courseService.getAvailableCourses()) {
            courseNameRatingList.add(CourseNameRating.builder().name(course.getName()).rating(course.getRating()).build());
        }
        builder.withDetail("courses", courseNameRatingList);
    }

    @Builder
    @Data
    private static class CourseNameRating {
        String name;
        int rating;

    }
}
