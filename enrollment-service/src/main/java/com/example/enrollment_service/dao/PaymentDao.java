package com.example.enrollment_service.dao;

import com.example.enrollment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
} 