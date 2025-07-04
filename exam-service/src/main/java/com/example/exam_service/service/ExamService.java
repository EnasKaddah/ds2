package com.example.exam_service.service;

import com.example.exam_service.dao.ExamDao;
import com.example.exam_service.feign.CourseServiceClient;
import com.example.exam_service.model.Exam;
import com.example.exam_service.model.ExamDto;
import com.example.exam_service.model.ExamWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;

    @Autowired
    private CourseServiceClient courseServiceClient;

    public ResponseEntity<String> addExam(ExamDto examDto) {
        Exam exam = new Exam();
        exam.setCourseId(examDto.getCourseId());
        exam.setTitle(examDto.getTitle());
        exam.setDescription(examDto.getDescription());
        try {
            exam.setDate(LocalDateTime.parse(examDto.getDate()));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use ISO format.");
        }
        examDao.save(exam);
        return ResponseEntity.ok("Exam added.");
    }

    public ResponseEntity<List<ExamWrapper>> listExams() {
        List<Exam> exams = examDao.findAll();
        List<ExamWrapper> wrappers = exams.stream()
                .map(e -> new ExamWrapper(
                        e.getId(),
                        courseServiceClient.getCourseById(e.getCourseId()),
                        e.getTitle(),
                        e.getDescription(),
                        e.getDate().toString()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wrappers);
    }

    public ResponseEntity<List<ExamWrapper>> listExamsByCourse(Integer courseId) {
        List<Exam> exams = examDao.findByCourseId(courseId);
        List<ExamWrapper> wrappers = exams.stream()
                .map(e -> new ExamWrapper(
                        e.getId(),
                        courseServiceClient.getCourseById(e.getCourseId()),
                        e.getTitle(),
                        e.getDescription(),
                        e.getDate().toString()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wrappers);
    }
} 