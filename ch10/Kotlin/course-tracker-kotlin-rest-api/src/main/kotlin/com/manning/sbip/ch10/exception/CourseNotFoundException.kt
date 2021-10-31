package com.manning.sbip.ch10.exception

import org.springframework.http.HttpStatus

class CourseNotFoundException(status: HttpStatus, message: String) : RuntimeException()