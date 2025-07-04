package com.example.enrollment_service.dao;

import com.example.enrollment_service.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentDao extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByUserId(Integer userId);
} 