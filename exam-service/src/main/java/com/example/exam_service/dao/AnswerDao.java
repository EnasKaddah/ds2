package com.example.exam_service.dao;

import com.example.exam_service.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerDao extends JpaRepository<Answer, Integer> {
    List<Answer> findByStudentIdAndExamId(Integer studentId, Integer examId);
} 