package com.example.exam_service.controller;

import com.example.exam_service.model.Answer;
import com.example.exam_service.model.AnswerDto;
import com.example.exam_service.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerDto dto) {
        return answerService.submitAnswer(dto);
    }

        @GetMapping("/list/{studentId}/{examId}")
    public ResponseEntity<List<Answer>> listAnswers(@PathVariable Integer studentId, @PathVariable Integer examId) {
        return answerService.listAnswers(studentId, examId);
    }
} 