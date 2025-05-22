package org.example.repository;

import org.example.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
    Optional<Course> findByQuizId(Long quizId);
    Optional<List<Course>> findByUserId(Long userId);
    Optional<List<Long>> findByCourseIdWithIdsOnly(Long courseId);
}
