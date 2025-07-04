package com.example.exam_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper {
    private Integer id;
    private Integer exam;
    private String text;
    private String options;

    public QuestionWrapper(Integer id, Exam exam, String text, String options) {
        this.id = id;
        this.exam = exam.getId();
        this.text = text;
        this.options = options;
    }
}