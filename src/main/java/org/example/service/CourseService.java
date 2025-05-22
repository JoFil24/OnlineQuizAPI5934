package org.example.service;

import org.example.model.Course;
import org.example.model.Submission;
import org.example.repository.CourseRepository;
import org.example.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldnt find course"));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course findByTitle(String title){
        return courseRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Couldnt find course"));
    }

    public Course findByQuizId(Long quizId){
        return courseRepository.findByQuizId(quizId).orElseThrow(() -> new RuntimeException("Couldnt find course"));
    }

    public List<Course> findByUserId(Long userId){
        return courseRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Couldnt find course"));
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
