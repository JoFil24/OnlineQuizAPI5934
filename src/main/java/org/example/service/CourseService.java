package org.example.service;

import org.example.model.*;
import org.example.repository.CourseRepository;
import org.example.repository.QuizRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldnt find course"));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Quiz> getQuizzesByCourse(Long courseId){
        Optional<Course> opCourse = courseRepository.findById(courseId);

        if(opCourse.isPresent()){
            Course course = opCourse.get();
            return course.getQuizzes();
        }
        else {
            return new ArrayList<>();
        }
    }

    public Course addQuizToCourse(Long courseId, String title, int timeLimit){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        Quiz quiz = new Quiz(title, timeLimit, course);

        course.getQuizzes().add(quiz);

        quizRepository.save(quiz);
        return courseRepository.save(course);
    }

    public Course addStudentToCourse(Long courseId, Long studentId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        course.getStudents().add(student);
        student.getCourse().add(course);

        studentRepository.save(student);
        return courseRepository.save(course);
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void removeStudentFromCourse(Long id, Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        course.getStudents().remove(student);
        courseRepository.save(course);

        student.getCourse().remove(course);
        studentRepository.save(student);
    }

    public void deleteQuizFromCourse(Long id, Long quizId){
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        course.getQuizzes().remove(quiz);
        this.saveCourse(course);

        quizRepository.deleteById(quizId);
    }
}
