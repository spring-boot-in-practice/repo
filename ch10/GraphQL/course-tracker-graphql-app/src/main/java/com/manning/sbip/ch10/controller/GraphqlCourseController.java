package com.manning.sbip.ch10.controller;

import com.manning.sbip.ch10.model.Course;
import com.manning.sbip.ch10.model.Review;
import com.manning.sbip.ch10.repository.CourseRepository;
import com.manning.sbip.ch10.repository.ReviewRepository;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class GraphqlCourseController {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    public GraphqlCourseController(CourseRepository courseRepository, ReviewRepository reviewRepository) {
        this.courseRepository = courseRepository;
        this.reviewRepository = reviewRepository;
    }

    @QueryMapping
    Flux<Course> courses() {
        return this.courseRepository.findAll();
    }

    @QueryMapping
    Flux<Review> reviews(@Argument Integer courseId) {
        return this.reviewRepository.findByCourseId(courseId);
    }

    @QueryMapping
    Flux<Course> coursesByCategory(@Argument String category) {
        return this.courseRepository.findByCategory(category);
    }

    @SchemaMapping(typeName = "Course")
    Flux<Review> reviews(Course course) {
        return this.reviewRepository.findByCourseId(course.getId());
    }

    @MutationMapping
    Mono<Course> addCourse(@Argument String name, @Argument String category, @Argument String description) {
        return this.courseRepository.save(new Course(null, name, category, description));
    }

    @MutationMapping
    Mono<Review> addReview(@Argument Integer courseId, @Argument String reviewerName, @Argument Integer rating, @Argument String comment) {
        System.err.println(courseId + "," + reviewerName + "," + rating + "," + comment);
        return this.reviewRepository.save(new Review(null, courseId, reviewerName, rating, comment));
    }

    @SubscriptionMapping
    Flux<Review> reviewEvents(@Argument Integer courseId) {
        return this.courseRepository.findById(courseId)
                .flatMapMany(review -> this.reviewRepository.findByCourseId(review.getId()))
                .delayElements(Duration.ofSeconds(1))
                .take(5);
    }
}
