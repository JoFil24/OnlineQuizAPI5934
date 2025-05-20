package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer timeLimitSeconds;

    @OneToMany(mappedBy = "quiz", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String title, Integer timeLimitSeconds) {
        this.title = title;
        this.timeLimitSeconds = timeLimitSeconds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTimeLimitSeconds() {
        return timeLimitSeconds;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeLimitSeconds(Integer timeLimitSeconds) {
        this.timeLimitSeconds = timeLimitSeconds;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
