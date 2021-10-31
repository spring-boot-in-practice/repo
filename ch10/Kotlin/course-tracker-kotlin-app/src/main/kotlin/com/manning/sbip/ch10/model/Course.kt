package com.manning.sbip.ch10.model

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Courses")
class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = 0,

    @Column(name = "NAME")
    @NotEmpty(message = "Course name field can't be empty")
    var name: String? = "",

    @Column(name = "CATEGORY")
    @NotEmpty(message = "Course category field can't be empty")
    var category: String? = "",

    @Column(name = "RATING")
    @Min(value = 1)
    @Max(value = 5)
    var rating : Int? = 0,

    @Column(name = "DESCRIPTION")
    @NotEmpty(message = "Course description field can't be empty")
    var description: String? = ""
)
