package com.manning.sbip.ch10

import com.manning.sbip.ch10.model.Course
import com.manning.sbip.ch10.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans

@SpringBootApplication
class CourseTrackerKotlinAppApplication {}

fun main(args: Array<String>) {
	runApplication<CourseTrackerKotlinAppApplication>(*args) {
		addInitializers(
				beans {
					bean {
						CommandLineRunner {
							val courseRepository = ref<CourseRepository>()
							courseRepository.save(Course(1, "Spring Boot in Practice", "Spring", 5, "Spring Boot in Practice is intended to demonstrate various practical use of Spring Boot"))
						}
					}
				}
		)
	}
}

