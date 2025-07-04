package com.example.exam_service.dao;

import com.example.exam_service.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamDao extends JpaRepository<Exam, Integer> {
    List<Exam> findByCourseId(Integer courseId);
} 