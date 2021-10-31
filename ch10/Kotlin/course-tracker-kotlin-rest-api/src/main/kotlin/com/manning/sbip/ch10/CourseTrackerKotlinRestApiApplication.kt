package com.manning.sbip.ch10

import com.manning.sbip.ch10.model.Course
import com.manning.sbip.ch10.service.CourseHandler
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.servlet.function.body
import org.springframework.web.servlet.function.router

@SpringBootApplication
class CourseTrackerKotlinRestApiApplication

fun main(args: Array<String>) {
    runApplication<CourseTrackerKotlinRestApiApplication>(*args) {
        addInitializers(
                beans {
                         bean("passwordEncoder") {
                             BCryptPasswordEncoder()
                         }
                         bean {
                             fun user(user : String, password: String, vararg  roles : String) = User.builder().username(user).password(ref<PasswordEncoder>().encode(password)).roles(*roles).build()
                             InMemoryUserDetailsManager(user("user", "password", "USER"), user("admin", "password", "ADMIN"))
                         }

                    bean {
                        CommandLineRunner {
                            val courseHandler = ref<CourseHandler>()
                            courseHandler.createBulkCourses(
                                    arrayListOf(
                                            Course(1, "Spring Boot in Practice", "Spring", 5, "Spring Boot in Practice"),
                                            Course(2, "Spring Boot in Action", "Spring", 5, "Spring Boot in Action"),
                                            Course(3, "Spring Boot Start Here", "Spring", 4, "Spring Boot Start Here")
                                    )
                            )
                        }
                    }

                    bean {
                        router {
                            val courseHandler = ref<CourseHandler>()
                            "/courses".nest {
                                GET("/{id}") { ok().body(courseHandler.findCourseById(it.pathVariable("id").toLong())) }
                                GET("/") { ok().body(courseHandler.findAllCourses()) }
                                POST ("/") { ok().body(courseHandler.createCourse(it.body())) }
                                PUT("/") { ok().body(courseHandler.updateCourse(it.pathVariable("id").toLong(), it.body())) }
                                DELETE("/{id}") { ok().body(courseHandler.deleteCourseById(it.pathVariable("id").toLong())) }
                            }
                        }
                    }

                    bean {
                        val http = ref<HttpSecurity>()
                        http {
                            csrf {
                                disable()
                            }
                            httpBasic {}
                            authorizeRequests {
                                authorize(anyRequest, authenticated)
                            }
                        }
                        http.build()
                    }
                }

        )
    }
}
