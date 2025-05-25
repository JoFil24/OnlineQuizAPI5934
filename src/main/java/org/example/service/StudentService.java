package org.example.service;

import org.example.model.Course;
import org.example.model.Student;
import org.example.repository.CourseRepository;
import org.example.repository.QuizRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            Set<Student> studentsSet = course.getStudents();
            return new ArrayList<>(studentsSet);
        } else {
            return new ArrayList<>();
        }
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

    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        // Removing student from courses
        for (Course course : student.getCourse()) {
            course.getStudents().remove(student);
        }

        studentRepository.delete(student);
    }
}
