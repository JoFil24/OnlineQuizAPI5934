package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    @JsonIgnore
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    private Integer selectedChoiceIndex;

    public Answer() {}

    public Answer(Question question, Integer selectedChoiceIndex) {
        this.question = question;
        this.selectedChoiceIndex = selectedChoiceIndex;
    }

    public Long getId() {
        return id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public Question getQuestion() {
        return question;
    }

    public Integer getSelectedChoiceIndex() {
        return selectedChoiceIndex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setSelectedChoiceIndex(Integer selectedChoiceIndex) {
        this.selectedChoiceIndex = selectedChoiceIndex;
    }
}
