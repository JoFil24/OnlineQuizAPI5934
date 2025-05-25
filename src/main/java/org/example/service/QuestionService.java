package org.example.service;

import org.example.model.Question;
import org.example.model.Quiz;
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

    public Question saveQuestion(Question questionDetails){
        //if the questionId doesnt exist, then that means it came
        //from the post method and it needs to be created
        //else just change some attributes
        if(questionDetails.getId() == null){
            Question question = new Question(questionDetails.getQuiz(), questionDetails.getText(), questionDetails.getChoices(), questionDetails.getCorrectChoiceIndex());
            return questionRepository.save(question);
        }
        return questionRepository.save(questionDetails);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

    public List<Question> getQuestionByQuizId(Long quizId){
        return questionRepository.findByQuizId(quizId).orElseThrow(() -> new RuntimeException("Question not found"));
    }
}
