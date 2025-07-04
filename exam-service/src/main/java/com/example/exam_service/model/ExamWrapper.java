package com.example.exam_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamWrapper {
    private Integer id;
    private Object course;
    private String title;
    private String description;
    private String date;

    public ExamWrapper(Integer id, Object course, String title, String description, String date) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.description = description;
        this.date = date;
    }
}