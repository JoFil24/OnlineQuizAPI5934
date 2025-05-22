package org.example.service;

import org.example.model.Question;
import org.example.model.Quiz;
import org.example.model.Submission;
import org.example.model.User;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Submission getSubmissionByUsername(String username) {
        return submissionRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Could not find username"));
    }

    public List<Submission> getSubmissionByUserId(Long userId) {
        return submissionRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Could not find user"));
    }

    public List<Submission> getSubmissionsByQuizId(Long quizId) {
        return submissionRepository.findByQuizId(quizId).orElseThrow(() -> new RuntimeException("Could not find submissions"));
    }

    public List<Submission> fetchLeaderboard(Long quizId) {
        Optional<List<Submission>> submissionsOpt = submissionRepository.findByQuizId(quizId);

        if (submissionsOpt.isPresent()) {
            List<Submission> submissions = submissionsOpt.get();

            submissions.sort(Comparator.comparingInt(Submission::getScore).reversed());

            return submissions;
        } else {
            return List.of();
        }
    }

    public List<Submission> generateReport(Long userId, Long courseId) {
            List<Long> quizIds = courseRepository.findByCourseIdWithIdsOnly(courseId).orElseThrow(() -> new RuntimeException("Could not find submissions"));

            List<Submission> report = new ArrayList<>();

            for(Long quizId: quizIds){
                if(submissionRepository.findByUserIdAndQuizId(userId, quizId).orElse(null) != null){
                    report.add(submissionRepository.findByUserIdAndQuizId(userId, quizId).orElse(null));
                }
            }

            return report;
    }

    public Submission submit(Long userId, Long quizId, Map<Long, Integer> answers){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Couldn't find user"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Couldn't find user"));

        int score = 0;

        List<Question> questions = quiz.getQuestions();

        for(Map.Entry<Long, Integer> answer: answers.entrySet()){
            //It might be weird seeing this as the only comment
            //But this needs explaining
            //First it checks whether the key (Id of question) is present
            //if yes then the value of the map entry(integer which is the choice)
            //is equivalent to the correct choice
            //we get the correct choice by performing get()
            // to convert from optional to question
            //When it is question, then we get the correct choice index
            if(questionRepository.findById(answer.getKey()).isPresent()){
                if(answer.getValue().equals(questionRepository.findById(answer.getKey()).get().getCorrectChoiceIndex())){
                    score += 1;
                }
            }
        }

        Submission submission = new Submission(quiz, user, LocalDateTime.now(), answers, score);

        user.getSubmissions().add(submission);
        quiz.getSubmissions().add(submission);
        return submission;
    }

    public Submission getSubmissionForUser(Long userId, Long quizId) {
        return submissionRepository.findByUserIdAndQuizId(userId, quizId).orElseThrow(() -> new RuntimeException("Couldnt find submission"));
    }

    public Submission saveSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    public void deleteSubmission(Long submissionId) {
        submissionRepository.deleteById(submissionId);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(Long submissionId) {
        return submissionRepository.findById(submissionId).orElseThrow(() -> new RuntimeException("Couldnt find the submission"));
    }
}
