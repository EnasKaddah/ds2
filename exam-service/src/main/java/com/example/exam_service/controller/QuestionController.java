package com.example.exam_service.controller;

import com.example.exam_service.model.Question;
import com.example.exam_service.model.QuestionDto;
import com.example.exam_service.model.QuestionWrapper;
import com.example.exam_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDto dto) {
        return questionService.addQuestion(dto);
    }

    @GetMapping("/list/{examId}")
    public ResponseEntity<List<QuestionWrapper>> listByExam(@PathVariable Integer examId) {
        return questionService.listByExam(examId);
    }
} 