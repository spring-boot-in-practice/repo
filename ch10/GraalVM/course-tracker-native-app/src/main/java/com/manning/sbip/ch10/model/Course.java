package com.manning.sbip.ch10.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {

    private Integer id;
    private String name;
    private String category;
    private Integer rating;
    private String description;
}
