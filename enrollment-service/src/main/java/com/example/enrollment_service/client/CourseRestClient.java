package com.example.enrollment_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CourseRestClient {
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "courseService", fallbackMethod = "fallbackGetTrainerId")
    public Integer getTrainerId(Integer courseId) {
        return restTemplate.getForObject("http://course-service/course/trainer/" + courseId, Integer.class);
    }

    public Integer fallbackGetTrainerId(Integer courseId, Throwable t) {
        System.err.println("Fallback for getTrainerId: " + t.getMessage());
        return -1;
    }

    @CircuitBreaker(name = "courseService", fallbackMethod = "fallbackGetCourseCost")
    public Double getCourseCost(Integer courseId) {
        return restTemplate.getForObject("http://course-service/course/cost/" + courseId, Double.class);
    }

    public Double fallbackGetCourseCost(Integer courseId, Throwable t) {
        System.err.println("Fallback for getCourseCost: " + t.getMessage());
        return -1.0;
    }
} 