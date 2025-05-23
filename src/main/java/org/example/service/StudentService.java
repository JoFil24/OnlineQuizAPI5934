package org.example.service;

import org.example.model.Student;
import org.example.repository.QuizRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Couldn't find student"));
    }

    public Student createStudent (Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Long studentId, Student studentDetails) {
        return studentRepository.findById(studentId).map(student -> {
            student.setName(studentDetails.getName());

            return studentRepository.save(student);
        });
    }

    public void deleteStudent (Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
