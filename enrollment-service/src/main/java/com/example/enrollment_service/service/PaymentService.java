package com.example.enrollment_service.service;

import com.example.enrollment_service.dao.PaymentDao;
import com.example.enrollment_service.model.Payment;
import com.example.enrollment_service.client.UserRestClient;
import com.example.enrollment_service.client.CourseRestClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private CourseRestClient courseRestClient;

    public Payment processPayment(Payment payment, Integer courseId) {
        Double courseCost = courseRestClient.getCourseCost(courseId);
        if (courseCost == null) {
            throw new RuntimeException("Course not found or service unavailable");
        }
        Integer studentBalance = userRestClient.getWalletBalance(payment.getStudentWalletId());
        if (studentBalance == null) {
            throw new RuntimeException("Student wallet not found or service unavailable");
        }
        if (studentBalance < courseCost) {
            throw new RuntimeException("Insufficient funds");
        }
        userRestClient.updateWalletBalance(payment.getStudentWalletId(), -courseCost.intValue());
        userRestClient.updateWalletBalance(payment.getTrainerWalletId(), courseCost.intValue());
        payment.setAmount(courseCost);
        return paymentDao.save(payment);
    }

}