package org.example.service;

import org.example.model.Question;
import org.example.model.Quiz;
import org.example.repository.QuestionRepository;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Quiz addQuestionToQuiz(Long quizId, Long questionId){
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuiz(quiz);
        quiz.getQuestions().add(question);

        questionRepository.save(question);
        return quizRepository.save(quiz);
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

    public void removeQuestionFromQuiz(Long id, Long questionId){
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldnt find question"));
        question.getQuiz().getQuestions().remove(question);
        quizRepository.save(question.getQuiz());
    }
}
