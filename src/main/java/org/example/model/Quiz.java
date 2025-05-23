package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer timeLimitSeconds;

    @OneToMany(mappedBy = "quiz", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<Submission> submissions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    public Quiz() {
    }

    public Quiz(String title, Integer timeLimitSeconds, Course course) {
        this.title = title;
        this.timeLimitSeconds = timeLimitSeconds;
        this.course = course;
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

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public Course getCourse() {
        return course;
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

    public void setCourse(Course course) {
        this.course = course;
    }

    public int calculateScore(Map<Long, Integer> answers){
        int score = 0;

        for(Question question: questions){
            Integer selectedChoice = answers.get(question.getId());
            if(selectedChoice != null && selectedChoice == question.getCorrectChoiceIndex()){
                score++;
            }
        }

        return score;
    }
}
