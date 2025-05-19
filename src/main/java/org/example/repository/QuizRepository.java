package org.example.repository;

import org.example.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findByTitle(String title);
    Quiz findByUser(Long userId);

}
