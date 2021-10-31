package com.manning.sbip.ch10.repository;

import com.manning.sbip.ch10.model.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Integer> {

    Flux<Review> findByCourseId(Integer courseId);
}
