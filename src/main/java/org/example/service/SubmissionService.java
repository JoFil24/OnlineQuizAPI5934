package org.example.service;

import org.example.model.Quiz;
import org.example.model.Submission;
import org.example.repository.QuizRepository;
import org.example.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission getSubmissionByUsername(String username) {
        return submissionRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Could not find username"));
    }

    public Submission getSubmissionByUserId(Long userId) {
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

    public Submission generateReport(Long userId) {
        return submissionRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Couldn't find user"));
    }

    public int getPointsForUser(Long userId, Long quizId) {
        return submissionRepository.getPointsForUser(userId, quizId);
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
