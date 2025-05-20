package org.example.service;

import org.example.model.Quiz;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz getQuizByTitle(String title) {
        return quizRepository.findByTitle(title);
    }
    public Quiz getQuizByUser (Long userId) {
        return quizRepository.findByUser(userId);
    }
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Couldn't find quiz"));
    }
}
