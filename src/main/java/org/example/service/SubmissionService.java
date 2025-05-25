package org.example.service;

import org.example.model.*;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Submission> getSubmissionByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId).orElseThrow(() -> new RuntimeException("Could not find student"));
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

    public List<Submission> generateReport(Long studentId, Long courseId) {
            List<Quiz> quizzes = quizRepository.findByCourseId(courseId).orElseThrow(() -> new RuntimeException("Could not find submissions"));

            List<Submission> report = new ArrayList<>();

            for(Quiz quiz: quizzes){
                if(submissionRepository.findByStudentIdAndQuizId(studentId, quiz.getId()).orElse(null) != null){
                    report.add(submissionRepository.findByStudentIdAndQuizId(studentId, quiz.getId()).orElse(null));
                }
            }

            return report;
    }

    public Submission submit(Long studentId, Long quizId, List<Integer> tempAnswers){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Couldn't find student"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Couldn't find quiz"));
        List<Question> questions = quiz.getQuestions();
        List<Answer> answers = new ArrayList<>();

        if(questions.size() == tempAnswers.size()){
            for(int i = 0; i < questions.size(); i++){
                Answer newAnswer = new Answer(questions.get(i), tempAnswers.get(i));
                answers.add(newAnswer);
                questions.get(i).getAnswers().add(answers.get(i));
            }
        }

        answerRepository.saveAll(answers);

        int score = quiz.calculateScore(answers);

        //List<Question> questions = quiz.getQuestions();

        //for(Map.Entry<Long, Integer> answer: answers.entrySet()){
            //It might be weird seeing this as the only comment
            //But this needs an explanation
            //First it checks whether the key (Id of question) is present
            //if yes then the value of the map entry(integer which is the choice)
            //is equivalent to the correct choice
            //we get the correct choice by performing get()
            // to convert from optional to question
            //When it is question, then we get the correct choice index
            //if(questionRepository.findById(answer.getKey()).isPresent()){
            //    if(answer.getValue().equals(questionRepository.findById(answer.getKey()).get().getCorrectChoiceIndex())){
            //        score += 1;
            //    }
           // }
        //}

        Submission submission = new Submission(quiz, student);
        submission.setAnswers(answers);
        submission.setScore(quiz.calculateScore(answers));

        student.getSubmissions().add(submission);
        quiz.getSubmissions().add(submission);
        return submissionRepository.save(submission);
    }

    public Submission getSubmissionForStudent(Long studentId, Long quizId) {
        return submissionRepository.findByStudentIdAndQuizId(studentId, quizId).orElseThrow(() -> new RuntimeException("Couldnt find submission"));
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
