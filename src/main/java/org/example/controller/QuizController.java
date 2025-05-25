package org.example.controller;

import org.example.model.Question;
import org.example.model.Quiz;
import org.example.service.CourseService;
import org.example.service.QuestionService;
import org.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping("/course/{courseId}")
    public Quiz createQuiz(@RequestBody Quiz quiz, @PathVariable Long courseId) {
        quiz.setCourse(courseService.getCourseById(courseId));
        return quizService.saveQuiz(quiz);
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        quiz.setId(id);
        return quizService.saveQuiz(quiz);
    }

    @PutMapping("/{id}/add-question/{questionId}")
    public Quiz addQuestionToQuiz(@PathVariable Long id, @PathVariable Long questionId){
        return quizService.addQuestionToQuiz(id, questionId);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    @PutMapping("/{id}/remove-question/{questionId}")
    public void removeQuestionFromQuiz(@PathVariable Long id, @PathVariable Long questionId){
        quizService.removeQuestionFromQuiz(id, questionId);
    }
}
