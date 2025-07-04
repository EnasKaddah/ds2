package com.example.course_service.controller;

import com.example.course_service.model.Course;
import com.example.course_service.model.CourseDto;
import com.example.course_service.model.CourseWrapper;
import com.example.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    Environment environment;

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CourseDto courseDto) {
        System.out.println(environment.getProperty("local.server.port"));
        return courseService.addCourse(courseDto);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveCourse(@PathVariable Integer id) {
        return courseService.approveCourse(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseWrapper>> listApprovedCourses() {
        return courseService.listApprovedCourses();
    }

    @GetMapping("/cost/{courseId}")
    public ResponseEntity<Double> getCourseCost(@PathVariable Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course.getCost());
    }

    @GetMapping("/trainer/{courseId}")
    public ResponseEntity<Integer> getTrainerId(@PathVariable Integer courseId) {
        com.example.course_service.model.Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course.getTrainerId());
    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<CourseWrapper> getCourseById(@PathVariable Integer courseId) {
        com.example.course_service.model.Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        CourseWrapper wrapper = new CourseWrapper(
            course.getId(),
            course.getName(),
            course.getDescription(),
            course.getTrainerId(),
            course.getCost()
        );
        return ResponseEntity.ok(wrapper);
    }
} 