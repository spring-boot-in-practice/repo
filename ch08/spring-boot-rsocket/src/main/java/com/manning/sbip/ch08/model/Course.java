package com.manning.sbip.ch08.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course {

	private UUID courseId = UUID.randomUUID();
	private long created = Instant.now().getEpochSecond();
	private String courseName;
	
	public Course(String courseName) {
		this.courseName = courseName;
	}
}
