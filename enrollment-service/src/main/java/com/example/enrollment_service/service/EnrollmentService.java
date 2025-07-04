package com.example.enrollment_service.service;

import com.example.enrollment_service.dao.EnrollmentDao;
import com.example.enrollment_service.model.Enrollment;
import com.example.enrollment_service.model.EnrollmentDto;
import com.example.enrollment_service.model.EnrollmentWrapper;
import com.example.enrollment_service.model.Payment;
import com.example.enrollment_service.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.enrollment_service.client.UserRestClient;
import com.example.enrollment_service.client.CourseRestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private CourseRestClient courseRestClient;

    public ResponseEntity<String> enroll(EnrollmentDto dto) {
        try{
        Integer studentWalletId = userRestClient.getWalletIdByUserId(dto.getUserId());
        Integer trainerId = courseRestClient.getTrainerId(dto.getCourseId());
        Integer trainerWalletId = userRestClient.getWalletIdByUserId(trainerId);


        Payment payment = new Payment();
        payment.setStudentWalletId(studentWalletId);
        payment.setTrainerWalletId(trainerWalletId);

        paymentService.processPayment(payment, dto.getCourseId());

        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(dto.getUserId());
        enrollment.setCourseId(dto.getCourseId());
        enrollmentDao.save(enrollment);

        return ResponseEntity.ok("Enrollment and payment successful.");}
        catch (Exception e){
            return ResponseEntity.ok("something went wrong.");
        }

    }

    public ResponseEntity<List<EnrollmentWrapper>> listByUser(Integer userId) {
        List<Enrollment> list = enrollmentDao.findByUserId(userId);
        List<EnrollmentWrapper> wrappers = list.stream()
                .map(e -> new EnrollmentWrapper(e.getId(), e.getUserId(), e.getCourseId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wrappers);
    }


}