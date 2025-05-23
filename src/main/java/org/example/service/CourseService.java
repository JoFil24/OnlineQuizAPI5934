package org.example.service;

import org.example.model.*;
import org.example.repository.CourseRepository;
import org.example.repository.QuizRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Course addQuizToCourse(Long courseId, Long quizId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        quiz.setCourse(course);
        course.getQuizzes().add(quiz);

        quizRepository.save(quiz);
        return courseRepository.save(course);
    }

    public Course addUserToCourse(Long courseId, Long userId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        User user = userRepository.findById(courseId).orElseThrow(() -> new RuntimeException("User not found"));

        course.getUsers().add(user);
        user.getCourse().add(course);

        userRepository.save(user);
        return courseRepository.save(course);
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void removeUserFromCourse(Long id, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        course.getUsers().remove(user);
        courseRepository.save(course);

        user.getCourse().remove(course);
        userRepository.save(user);
    }

    public void deleteQuizFromCourse(Long id, Long quizId){
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        course.getQuizzes().remove(quiz);
        this.saveCourse(course);

        quizRepository.deleteById(quizId);
    }
}
