package com.manning.sbip.ch07.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "COURSES")
@Data
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotEmpty
	@Column(name = "NAME")
	private String name;

	@NotEmpty
	@Column(name = "CATEGORY")
	private String category;

	@Min(value = 1, message = "Minimum rating value is 1")
	@Max(value = 5, message = "maximum rating value is 5")
	@Column(name = "RATING")
	private int rating;

	@NotEmpty
	@Column(name = "DESCRIPTION")
	private String description;
}
