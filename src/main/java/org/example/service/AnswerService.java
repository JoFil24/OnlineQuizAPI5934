package org.example.service;

import org.example.model.Answer;
import org.example.model.Course;
import org.example.repository.AnswerRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private final AnswerRepository answerRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public Answer createAnswer(Answer answer) {
        answer.getSubmission().getAnswers().add(answer);
        answer.getQuestion().getAnswers().add(answer);

        //save the answer to its related submission and question
        submissionRepository.save(answer.getSubmission());
        questionRepository.save(answer.getQuestion());
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public Answer saveAnswer(Answer answer){
        return answerRepository.save(answer);
    }
}
