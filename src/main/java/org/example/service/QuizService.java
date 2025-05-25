package org.example.service;

import org.example.model.Question;
import org.example.model.Quiz;
import org.example.model.Student;
import org.example.model.Submission;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Quiz addQuestionToQuiz(Long quizId, Long questionId){
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuiz(quiz);
        quiz.getQuestions().add(question);

        questionRepository.save(question);
        return quizRepository.save(quiz);
    }

//    public Quiz addSubmissionToQuiz(Long quizId, Long studentId, Submission submission){
//        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
//        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

//        submission.setScore(quiz.calculateScore(submission.getAnswers()));
//        quiz.getSubmissions().add(submission);
//        submission.setQuiz(quiz);

//        submissionRepository.save(submission);
//        return quizRepository.save(quiz);
//    }

    public Quiz saveQuiz(Quiz quizDetails) {
        if(quizDetails.getId() == null){
            Quiz quiz = new Quiz(quizDetails.getTitle(), quizDetails.getTimeLimitSeconds(), quizDetails.getCourse());
            return quizRepository.save(quiz);
        }
        return quizRepository.save(quizDetails);
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
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Couldnt find question"));
        question.getQuiz().getQuestions().remove(question);
        quizRepository.save(question.getQuiz());
    }

    public List<Question> getQuestionsForQuiz(Long quizId){
        if(quizRepository.findById(quizId).isPresent()){
            return quizRepository.findById(quizId).get().getQuestions();
        }
        else{
            return new ArrayList<>();
        }
    }
}
