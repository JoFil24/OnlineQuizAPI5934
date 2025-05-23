package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionRepository;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

    public List<Question> getQuestionByQuizId(Long quizId){
        return questionRepository.findByQuizId(quizId).orElseThrow(() -> new RuntimeException("Question not found"));
    }
}
