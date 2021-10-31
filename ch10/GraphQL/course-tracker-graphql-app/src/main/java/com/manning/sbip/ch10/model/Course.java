package com.manning.sbip.ch10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Course {

    @Id
    private Integer id;
    private String name;
    private String category;
    private String description;

}
