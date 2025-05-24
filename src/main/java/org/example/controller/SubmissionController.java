package org.example.controller;

import org.example.model.Answer;
import org.example.model.Submission;
import org.example.model.Student;
import org.example.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService){
        this.submissionService = submissionService;
    }

    @GetMapping("/student/{studentId}")
    public List<Submission> getSubmissionByStudentId(@PathVariable Long studentId) {
        return submissionService.getSubmissionByStudentId(studentId);
    }
    @GetMapping("/quiz/{quizId}")
    public List<Submission> getSubmissionsByQuizId(@PathVariable Long quizId) {
        return submissionService.getSubmissionsByQuizId(quizId);
    }

    @GetMapping("/leaderboard/{quizId}")
    public List<Submission> fetchLeaderboard(@PathVariable Long quizId) {
        return submissionService.fetchLeaderboard(quizId);
    }

    @GetMapping("/quiz/{quizId}/student/{studentId}")
    public Submission getSubmissionForStudent(@PathVariable Long quizId, @PathVariable Long studentId){
        return submissionService.getSubmissionForStudent(studentId, quizId);
    }

    @GetMapping("/report/{studentId}/course/{courseId}")
    public List<Submission> generateReport(@PathVariable Long studentId, @PathVariable Long courseId) {
        return submissionService.generateReport(studentId, courseId);
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        Student student = submission.getStudent();
        student.addSubmission(submission);

        return submissionService.saveSubmission(submission);
    }

    @PostMapping("/submit/student/{studentId}/quiz/{quizId}")
    public Submission submit(@PathVariable Long studentId, @PathVariable Long quizId, @RequestBody List<Answer> answers){
        return submissionService.submit(studentId, quizId, answers);
    }

    @GetMapping
    public List<Submission> getAllSubmissions(){
        List<Submission> submissions = submissionService.getAllSubmissions();
        return submissions;
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
