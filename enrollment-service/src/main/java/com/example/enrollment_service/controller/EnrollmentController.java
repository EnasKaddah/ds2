package com.example.enrollment_service.controller;

import com.example.enrollment_service.model.EnrollmentDto;
import com.example.enrollment_service.model.EnrollmentWrapper;
import com.example.enrollment_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestBody EnrollmentDto dto) {
        return enrollmentService.enroll(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentWrapper>> listByUser(@PathVariable Integer userId) {
        return enrollmentService.listByUser(userId);
    }
} 