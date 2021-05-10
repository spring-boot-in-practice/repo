package com.manning.sbip.ch06.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "COURSES")
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	@NotEmpty(message = "Course name field can't be empty")
	private String name;

	@Column(name = "CATEGORY")
	@NotEmpty(message = "Course category field can't be empty")
	private String category;

	@Column(name = "RATING")
	@Min(value = 1)
	@Max(value = 5)
	private int rating;

	@Column(name = "DESCRIPTION")
	@NotEmpty(message = "Course description field can't be empty")
	private String description;
}
