package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<Submission> submissions = new ArrayList<>();

    @ManyToMany(mappedBy = "students")
//    @ManyToMany
//    @JoinTable(
//        name = "student_course",
//        joinColumns = @JoinColumn(name = "student_id"),
//        inverseJoinColumns = @JoinColumn(name = "course_id")
 //   )
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public Set<Course> getCourse() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubmission(Submission submission) {
        this.submissions.add(submission);
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
