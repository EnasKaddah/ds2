package com.example.exam_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service")
public interface CourseServiceClient {
    @GetMapping("/course/list")
    Object getAllCourses(); // Replace Object with List<CourseWrapper> if available

    @GetMapping("/course/cost/{courseId}")
    Double getCourseCost(@PathVariable("courseId") Integer courseId);

    @GetMapping("/course/get/{courseId}")
    Object getCourseById(@PathVariable("courseId") Integer courseId); // Replace Object with CourseWrapper if available
} 