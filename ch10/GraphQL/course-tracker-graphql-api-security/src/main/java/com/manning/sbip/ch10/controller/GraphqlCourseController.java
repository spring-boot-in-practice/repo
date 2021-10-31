package com.manning.sbip.ch10.controller;

import com.manning.sbip.ch10.model.Course;
import com.manning.sbip.ch10.model.Review;
import com.manning.sbip.ch10.repository.CourseRepository;
import com.manning.sbip.ch10.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class GraphqlCourseController {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    @QueryMapping
    @PreAuthorize("hasRole('USER')")
    Flux<Course> courses() {
        return this.courseRepository.findAll();
    }

    @QueryMapping
    //@PreAuthorize("hasRole('USER')")
    Flux<Review> reviews(@Argument Integer courseId) {
        return this.reviewRepository.findByCourseId(courseId);
    }

    @QueryMapping
    //@PreAuthorize("hasRole('USER')")
    Flux<Course> coursesByCategory(@Argument String category) {
        return this.courseRepository.findByCategory(category);
    }

    @SchemaMapping(typeName = "Course")
    Flux<Review> reviews(Course course) {
        return this.reviewRepository.findByCourseId(course.getId());
    }

    @MutationMapping
    //@PreAuthorize("hasRole('ADMIN')")
    Mono<Course> addCourse(@Argument String name, @Argument String category, @Argument String description) {
        return this.courseRepository.save(new Course(null, name, category, description));
    }

    @MutationMapping
    //@PreAuthorize("hasRole('ADMIN')")
    Mono<Review> addReview(@Argument Integer courseId, @Argument String reviewerName, @Argument Integer rating, @Argument String comment) {
        return this.reviewRepository.save(new Review(null, courseId, reviewerName, rating, comment));
    }
}
