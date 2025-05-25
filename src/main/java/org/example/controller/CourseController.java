package org.example.controller;

import org.example.model.Course;
import org.example.model.Quiz;
import org.example.model.Student;
import org.example.service.CourseService;
import org.example.service.QuizService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuizService quizService;

    @GetMapping
    public List<Course> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return courses;
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsByCourse(@PathVariable Long id){
        return studentService.getStudentsByCourse(id);
    }

    @GetMapping("/{id}/quizzes")
    public List<Quiz> getQuizzesByCourse(@PathVariable Long id){
        List<Quiz> quizzes = courseService.getQuizzesByCourse(id);
        return quizzes;
    }

    @PutMapping("/{id}/add-quiz")
    public Course addQuizToCourse(@PathVariable Long id, @RequestBody Quiz quiz){
        return courseService.addQuizToCourse(id, quiz.getTitle(), quiz.getTimeLimitSeconds());
    }

    @PutMapping("/{id}/add-student/{studentId}")
    public Course addStudentToCourse(@PathVariable Long id, @PathVariable Long studentId){
        return courseService.addStudentToCourse(id, studentId);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course){
        course.setId(id);
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable  Long id){
        courseService.deleteCourse(id);
    }

    @PutMapping("/{id}/remove-quiz/{quizId}")
    public void deleteQuizFromCourse(@PathVariable Long id, @PathVariable Long quizId){
        courseService.deleteQuizFromCourse(id, quizId);
    }

    @PutMapping("/{id}/remove-student/{studentId}")
    public void deleteStudentFromCourse(@PathVariable Long id, @PathVariable Long studentId){
        courseService.removeStudentFromCourse(id, studentId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }
}
