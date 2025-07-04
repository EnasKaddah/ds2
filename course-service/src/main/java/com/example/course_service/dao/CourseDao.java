package com.example.course_service.dao;

import com.example.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {
    List<Course> findByApprovedTrue();
} 