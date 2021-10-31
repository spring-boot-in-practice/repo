package com.manning.sbip.ch10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Review {

    @Id
    private Integer id;
    private Integer courseId;
    private String reviewerName;
    private Integer rating;
    private String comment;
}
