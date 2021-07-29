package com.manning.sbip.ch08.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.manning.sbip.ch08.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebClientApi {

	private static final String BASE_URL = "http://localhost:8080/courses/";

	private WebClient webClient;

	public WebClientApi() {
		this.webClient = WebClient.builder().baseUrl(BASE_URL).build();
	}

	public Mono<ResponseEntity<Course>> postNewCourse(Course course) {
		return this.webClient.post().body(Mono.just(course), Course.class).retrieve().toEntity(Course.class)
				.doOnSuccess(result -> System.out.println("POST " + result));
	}

	public Mono<Course> updateCourse(String id, String name, String category, int rating, String description) {
		return this.webClient.put().uri("{id}", id)
				.body(Mono.just(Course.builder().id(id).name(name).category(category).rating(rating)
						.description(description).build()), Course.class)
				.retrieve().bodyToMono(Course.class)
				.doOnSuccess(result -> System.out.println("Update Course: " + result));
	}

	public Mono<Course> getCourseById(String id) {
		return this.webClient.get().uri("{id}", id).retrieve().bodyToMono(Course.class)
				.doOnSuccess(c -> System.out.println(c)).doOnError((e) -> System.err.println(e.getMessage()));
	}

	public Flux<Course> getAllCourses() {
		return this.webClient.get().retrieve().bodyToFlux(Course.class).doOnNext(c -> System.out.println(c))
				.doOnError((e) -> System.err.println(e.getMessage()));
	}

	public Mono<Void> deleteCourse(String id) {
		return this.webClient.delete().uri("{id}", id).retrieve().bodyToMono(Void.class)
				.doOnSuccess(result -> System.out.println("DELETE " + result))
				.doOnError((e) -> System.err.println(e.getMessage()));
	}
}
