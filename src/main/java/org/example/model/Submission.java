package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;

public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime submittedAt;
    private Map<Long, Integer> answers;
    private int score;
    private boolean graded;

    public Submission() {
    }

    public Submission(Quiz quiz, User user, LocalDateTime submittedAt, Map<Long, Integer> answers, int score, boolean graded) {
        this.quiz = quiz;
        this.user = user;
        this.submittedAt = submittedAt;
        this.answers = answers;
        this.score = score;
        this.graded = graded;
    }

    public Long getId() {
        return id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public Map<Long, Integer> getAnswers() {
        return answers;
    }

    public int getScore() {
        return score;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void setAnswers(Map<Long, Integer> answers) {
        this.answers = answers;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }
}