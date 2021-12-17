[![Spring Boot in Practice CI](https://github.com/spring-boot-in-practice/repo/actions/workflows/ci-pipeline.yml/badge.svg)](https://github.com/spring-boot-in-practice/repo/actions/workflows/ci-pipeline.yml)

# Spring Boot in Practice

<a href="https://www.manning.com/books/spring-boot-in-practice?utm_source=musib&utm_medium=affiliate&utm_campaign=book_musib_spring_3_16_21&a_aid=musib&a_bid=27d46a98"><img src="https://github.com/spring-boot-in-practice/repo/blob/main/metadata/BookCover.png" alt="The book cover of 'Spring Boot in Practice' by Somnath Musib" align="left" height="200px" /></a>

[Spring Boot in Practice](https://www.manning.com/books/spring-boot-in-practice?utm_source=musib&utm_medium=affiliate&utm_campaign=book_musib_spring_3_16_21&a_aid=musib&a_bid=27d46a98) is a Spring Boot book written by [Somnath Musib](https://musibs.github.io) and published by Manning Publications. It's currently available through the Manning Early Access Program (MEAP). This book covers dozens of handy Spring Boot development techniques, from basic functions to hidden features you probably didn’t even know existed. Each recipe is built around a real-world problem and shows you how Spring Boot can provide a simple and elegant solution.

This repository contains the source code accompanying the book.

## What will you learn?

In Spring Boot in Practice you will learn:

- A comprehensive introduction to Spring Boot’s features
- Configuration management, logging, and monitoring Spring Boot applications
- Effective methods for database communication
- Utilizing Spring Security and securing your Spring application in production
- Designing and developing microservices and RESTful APIs with Spring Boot
- Microservice versioning, documentation, and security
- Reactive application development and reactive data access with WebSocket and RSocket
- Deploying Spring Boot applications on Kubernetes and major cloud platforms
- Implementing containerization in a Spring Boot application
- Using Spring Boot with Kotlin, GraalVM and GraphQL

Spring Boot in Practice is full of practical recipes for troubleshooting common development problems with Spring Boot. I have spent years building applications with Spring and share my extensive experience in this focused guide. You’ll master Spring Data, Spring Security, and other Spring-centric solutions for the common business problems you face every day as a software developer.

## Gradle and Maven

The code samples in the book use Apache Maven as the build tool. If you prefer Gradle over Maven, here's a table mapping Maven commands to Gradle:

| Maven                            | Gradle                     |
| -------------------------------- | -------------------------- |
| `./mvnw clean`                   | `./gradlew clean`          |
| `./mvnw install`                 | `./gradlew build`          |
| `./mvnw test`                    | `./gradlew test`           |
| `./mvnw repackage`               | `./gradlew bootJar`        |
| `./mvnw spring-boot:run`         | `./gradlew bootRun`        |
| `./mvnw spring-boot:build-image` | `./gradlew bootBuildImage` |

## Chapters

- Chapter 01 Booting Spring Boot
- Chapter 02 Spring Boot Common Tasks
- Chapter 03 Database Access with Spring Data
- Chapter 04 Spring Boot - Autoconfiguration and Actuator
- Chapter 05 Securing Spring Boot Applications
- Chapter 06 Implementing Additional Security with Spring Security
- Chapter 07 Developing RESTful Web Services with Spring Boot
- Chapter 08 Reactive Spring Boot Application Development
- Chapter 09 Deploying Spring Boot Applications
- Chapter 10 Spring Boot with Kotlin, Native Image and GraphQL
- Appendix A Generating and Building Spring Boot projects
- Appendix B Spring MVC and Thymeleaf Template Engine

## Book forum

Feel free to submit questions, feedback, or errata to the forum dedicated to "Spring Boot in Practice": https://livebook.manning.com/book/spring-boot-in-practice/.

## Contact the author

You are always welcome to contact me for questions, feedback, or suggestions. Feel free to reach out to me on Twitter, LinkedIn, or here on GitHub.
