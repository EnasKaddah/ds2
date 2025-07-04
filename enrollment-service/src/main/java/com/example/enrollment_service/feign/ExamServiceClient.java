package com.example.enrollment_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Models should be imported or replaced with Object if not available in this module

@FeignClient(name = "exam-service")
public interface ExamServiceClient {
    // ExamController
    @PostMapping("/exam/add")
    String addExam(@RequestBody Object examDto); // Replace Object with ExamDto if available

    @GetMapping("/exam/list")
    List<Object> listExams(); // Replace Object with ExamWrapper if available

    @GetMapping("/exam/list/{courseId}")
    List<Object> listExamsByCourse(@PathVariable("courseId") Integer courseId);

    // QuestionController
    @PostMapping("/question/add")
    String addQuestion(@RequestBody Object questionDto); // Replace Object with QuestionDto if available

    @GetMapping("/question/list/{examId}")
    List<Object> listQuestionsByExam(@PathVariable("examId") Integer examId);

    // AnswerController
    @PostMapping("/answer/submit")
    String submitAnswer(@RequestBody Object answerDto); // Replace Object with AnswerDto if available

    @GetMapping("/answer/list/{studentId}/{examId}")
    List<Object> listAnswers(@PathVariable("studentId") Integer studentId, @PathVariable("examId") Integer examId);
} 