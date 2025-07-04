package com.example.course_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseWrapper {
    private Integer id;
    private String name;
    private String description;
    private Integer trainerId;
    private Double cost;

    public CourseWrapper(Integer id, String name, String description, Integer trainerId, Double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.trainerId = trainerId;
        this.cost = cost;
    }
} 