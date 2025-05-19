package org.example.repository;

import org.example.model.Submission;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findByUsername(String name);
    Optional<Submission> findByUserId(Long userId);
    Optional<List<Submission>> findByQuizId(Long quizId);
    List<Submission> fetchLeaderboard(Long quizId);
    Optional<Submission> generateReport(Long userId);
    int getPointsForUser(Long userId, Long quizId);
}
