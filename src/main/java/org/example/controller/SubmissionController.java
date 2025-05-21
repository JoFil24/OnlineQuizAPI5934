package org.example.controller;

import org.example.model.Quiz;
import org.example.model.Submission;
import org.example.model.User;
import org.example.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {
    private SubmissionService submissionService;

    @GetMapping("/user/{userId}")
    public Submission getSubmissionByUserId(@PathVariable Long userId) {
        return submissionService.getSubmissionByUserId(userId);
    }
    @GetMapping("/quiz/{quizId}")
    public List<Submission> getSubmissionsByQuizId(@PathVariable Long quizId) {
        return submissionService.getSubmissionsByQuizId(quizId);
    }

    @GetMapping("/username/{username}")
    public Submission getSubmissionByUsername(@PathVariable String username){
        return submissionService.getSubmissionByUsername(username);
    }

    @GetMapping("/leaderboard/{quizId}")
    public List<Submission> fetchLeaderboard(@PathVariable Long quizId) {
        return submissionService.fetchLeaderboard(quizId);
    }

    @GetMapping("/quiz/{quizId}/user/{userId}")
    public int getPointsFromUser(@PathVariable Long quizId, @PathVariable Long userId){
        return submissionService.getPointsForUser(userId, quizId);
    }

    @GetMapping("/report/{userId}")
    public Submission generateReport(@PathVariable Long userId) {
        return submissionService.generateReport(userId);
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        User user = submission.getUser();
        user.addSubmission(submission);

        return submissionService.saveSubmission(submission);
    }

    @GetMapping
    public List<Submission> getAllSubmissions(){
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable Long id){
        return submissionService.getSubmissionById(id);
    }

    @PutMapping("/{id}")
    public Submission updateSubmission(@PathVariable Long id, @RequestBody Submission submission) {
        submission.setId(id);
        return submissionService.saveSubmission(submission);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
    }
}
