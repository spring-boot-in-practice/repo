package com.manning.sbip.ch08.controller;

import java.time.Duration;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.manning.sbip.ch08.model.Course;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class CourseController {

	@MessageMapping("request-response")
	public Mono<Course> requestResponse(final Course course) {
		log.info("Received request-response course details {} ", course);
		return Mono.just(new Course("Your course name: " + course.getCourseName()));
	}

	@MessageMapping("fire-and-forget")
	public Mono<Void> fireAndForget(final Course course) {
		log.info("Received fire-and-forget course details {} ", course);
		return Mono.empty();
	}

	@MessageMapping("request-stream")
	public Flux<Course> requestStream(final Course course) {
		log.info("Received request-stream course details {} ", course);
		return Flux.interval(Duration.ofSeconds(1))
				.map(index -> new Course("Your course name: " + course.getCourseName() + ". Response #" + index))
				.log();
	}
	
	@MessageMapping("stream-stream")
	public Flux<Course> channel(final Flux<Integer> settings) {
		log.info("Received stream-stream (channel) request... ");
		
		return settings.doOnNext(setting -> log.info("Requested interval is {} seconds", setting))
				.doOnCancel(() -> log.warn("Client cancelled the channel"))
				.switchMap(setting -> Flux.interval(Duration.ofSeconds(setting)).map(index -> new Course("Spring. Response #"+index)))
				.log();
	}
}
