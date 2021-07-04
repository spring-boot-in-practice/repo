package com.manning.sbip.ch07.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.manning.sbip.ch07.exception.CourseNotFoundException;

@ControllerAdvice
public class CourseTrackerGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {CourseNotFoundException.class})
	public ResponseEntity<?> handleCourseNotFound(CourseNotFoundException courseNotFoundException, WebRequest request) {
		return handleExceptionInternal(courseNotFoundException, 
				courseNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
