package org.example.controller;

import org.example.model.Course;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Course> findByUserId(@PathVariable Long userId){
        return courseService.findByUserId(userId);
    }

    @GetMapping("/quiz/{quizId}")
    public Course findByQuizId(@PathVariable Long quizId){
        return courseService.findByQuizId(quizId);
    }

    @GetMapping("/title/{title}")
    public Course findByTitle(@PathVariable String title){
        return courseService.findByTitle(title);
    }

    @PutMapping("/{id}/add-quiz/{quizId}")
    public Course addQuizToCourse(@PathVariable Long id, @PathVariable Long quizId){
        return courseService.addQuizToCourse(id, quizId);
    }

    @PutMapping("/{id}/add-user/{userId}")
    public Course addUserToCourse(@PathVariable Long id, @PathVariable Long userId){
        return courseService.addUserToCourse(id, userId);
    }

    @PutMapping("{id}")
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

    @PutMapping("/{id}/remove-user/{userId}")
    public void deleteUserFromCourse(@PathVariable Long id, @PathVariable Long userId){
        courseService.removeUserFromCourse(id, userId);
    }
}
