package org.example.repository;

import org.example.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<List<Submission>> findByStudentId(Long studentId);
    Optional<List<Submission>> findByQuizId(Long quizId);
    Optional<Submission> findByStudentIdAndQuizId(Long studentId, Long quizId);
}
