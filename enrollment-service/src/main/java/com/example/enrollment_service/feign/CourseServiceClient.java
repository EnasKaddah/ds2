package com.example.enrollment_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service")
public interface CourseServiceClient {
    @GetMapping("/course/trainer/{courseId}")
    Integer getTrainerId(@PathVariable("courseId") Integer courseId);

    @GetMapping("/course/cost/{courseId}")
    Double getCourseCost(@PathVariable("courseId") Integer courseId);
} 