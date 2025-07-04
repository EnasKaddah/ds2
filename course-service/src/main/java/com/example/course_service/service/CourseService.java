package com.example.course_service.service;

import com.example.course_service.dao.CourseDao;
import com.example.course_service.model.Course;
import com.example.course_service.model.CourseDto;
import com.example.course_service.model.CourseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public ResponseEntity<String> addCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setTrainerId(courseDto.getTrainerId());
        course.setApproved(false);
        course.setCost(courseDto.getCost());
        courseDao.save(course);
        return ResponseEntity.ok("Course added, pending approval.");
    }

    public ResponseEntity<String> approveCourse(Integer id) {
        Optional<Course> courseOpt = courseDao.findById(id);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setApproved(true);
            courseDao.save(course);
            return ResponseEntity.ok("Course approved.");
        } else {
            return ResponseEntity.status(404).body("Course not found.");
        }
    }

    public ResponseEntity<List<CourseWrapper>> listApprovedCourses() {
        List<Course> courses = courseDao.findByApprovedTrue();
        List<CourseWrapper> wrappers = courses.stream()
                .map(c -> new CourseWrapper(c.getId(), c.getName(), c.getDescription(), c.getTrainerId(), c.getCost()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wrappers);
    }

    public Course getCourseById(Integer courseId) {
        return courseDao.findById(courseId).orElse(null);
    }
} 