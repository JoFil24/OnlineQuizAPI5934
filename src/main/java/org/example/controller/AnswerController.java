package org.example.controller;

import org.example.model.Answer;
import org.example.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id).orElseThrow(() -> new RuntimeException("Couldnt find Answer"));
    }

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @PutMapping("/{id}")
    public Answer updateAnswer(@PathVariable Long id, @RequestBody Answer answer){
        answer.setId(id);
        return answerService.saveAnswer(answer);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
}
