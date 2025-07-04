package com.example.exam_service.controller;

import com.example.exam_service.model.ExamDto;
import com.example.exam_service.model.ExamWrapper;
import com.example.exam_service.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/add")
    public ResponseEntity<String> addExam(@RequestBody ExamDto examDto) {
        return examService.addExam(examDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ExamWrapper>> listExams() {
        return examService.listExams();
    }

    @GetMapping("/list/{courseId}")
    public ResponseEntity<List<ExamWrapper>> listExamsByCourse(@PathVariable Integer courseId) {
        return examService.listExamsByCourse(courseId);
    }
} 