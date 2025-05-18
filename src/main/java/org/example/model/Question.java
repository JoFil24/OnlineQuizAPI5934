package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    private String text;
    private List<String> choices;
    private int correctChoiceIndex;

    public Question() {
    }

    public Question(Quiz quiz, String text, List<String> choices, int correctChoiceIndex) {
        this.quiz = quiz;
        this.text = text;
        this.choices = choices;
        this.correctChoiceIndex = correctChoiceIndex;
    }

    public Long getId() {
        return id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public String getText() {
        return text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectChoiceIndex() {
        return correctChoiceIndex;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setCorrectChoiceIndex(int correctChoiceIndex) {
        this.correctChoiceIndex = correctChoiceIndex;
    }
}
