package com.manning.sbip.ch10.model

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Courses")
data class Course(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "ID")
    var id: Long? = 0,

    @field:Column(name = "NAME")
    @field:NotEmpty(message = "Course name field can't be empty")
    var name: String? = "",

    @field:Column(name = "CATEGORY")
    @field:NotEmpty(message = "Course category field can't be empty")
    var category: String? = "",

    @field:Column(name = "RATING")
    @Min(value = 1)
    @Max(value = 5)
    var rating : Int? = 0,

    @field:Column(name = "DESCRIPTION")
    @field:NotEmpty(message = "Course description field can't be empty")
    var description: String? = ""
)
