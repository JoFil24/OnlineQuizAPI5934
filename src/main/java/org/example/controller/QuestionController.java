package org.example.controller;

import org.example.model.Question;
import org.example.service.QuestionService;
import org.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionByQuizId(@PathVariable Long quizId){
        return questionService.getQuestionByQuizId(quizId);
    }

    @PostMapping("/quiz/{quizId}")
    public Question createQuestion(@PathVariable Long quizId, @RequestBody Question question) {
        question.setQuiz(quizService.getQuizById(quizId));
        return questionService.saveQuestion(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        question.setId(id);
        return questionService.saveQuestion(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
