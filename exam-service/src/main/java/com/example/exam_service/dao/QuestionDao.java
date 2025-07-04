package com.example.exam_service.dao;

import com.example.exam_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByExamId(Integer examId);
} 