package org.example.config;

import org.example.model.*;
import org.example.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(
            QuestionRepository questionRepository,
            QuizRepository quizRepository,
            SubmissionRepository submissionRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {
        return args -> {
            User u1 = new User("John");
            User u2 = new User("James");
            User u3 = new User("Katerina");
            userRepository.saveAll(Arrays.asList(u1, u2, u3));

            Course c1 = new Course("Mathematics");
            Course c2 = new Course("Physics");
            Course c3 = new Course("Chemistry");
            courseRepository.saveAll(Arrays.asList(c1, c2, c3));

            associateUserWithCourse(u1, c1);
            associateUserWithCourse(u1, c2);
            associateUserWithCourse(u2, c1);
            associateUserWithCourse(u2, c3);
            associateUserWithCourse(u3, c2);

            Quiz q1 = new Quiz("Math Mid-term", 3600, c1);
            Quiz q2 = new Quiz("Chemistry Lesson 5 Quiz", 420, c3);
            Quiz q3 = new Quiz("Physics Make-Up Exam", 1337, c2);
            Quiz q4 = new Quiz("Math Final", 3600, c1);
            Quiz q5 = new Quiz("Chemistry Trial", 600, c3);
            quizRepository.saveAll(Arrays.asList(q1, q2, q3, q4, q5));

            Question qu1 = new Question(q1, "Formula for area of square", Arrays.asList("a^2", "2a", "a/a"), 1);
            Question qu2 = new Question(q1, "Formula for hypotenuse of right-side triangle", Arrays.asList("a+b=c", "a*b=d", "a^2+b^2=c^2"), 3);
            Question qu3 = new Question(q1, "Natural numbers are:", Arrays.asList("Positive integers", "All integers", "positive numbers"), 1);
            Question qu4 = new Question(q2, "What is the first element on the periodic table", Arrays.asList("Tungsten", "Hydrogen", "Gadolinum"), 2);
            Question qu5 = new Question(q2, "True or false? Acids have a pH level below 7", Arrays.asList("True", "False"), 1);
            Question qu6 = new Question(q2, "K is the chemical symbol for which element?    ", Arrays.asList("Calcium", "Nitrogen", "Potassium"), 3);
            Question qu7 = new Question(q3, "Where does sound travel faster", Arrays.asList("Water", "Air"), 1);
            Question qu8 = new Question(q3, "What is the opposite of matter", Arrays.asList("No matter", "Anti-matter", "None of the above"), 2);
            Question qu9 = new Question(q3, "How many separate patents did Thomas Edison file", Arrays.asList("420", "1093", "69"), 2);
            Question qu10 = new Question(q4, "5+3", Arrays.asList("8", "10", "9"), 1);
            Question qu11 = new Question(q4, "What is the sum of 130+125+191", Arrays.asList("335", "456", "446", "426"), 3);
            Question qu12 = new Question(q4, "2+24/8", Arrays.asList("5", "6", "7"), 1);
            Question qu13 = new Question(q5, " At room temperature, what is the only metal that is in liquid form?", Arrays.asList("Gold", "Silver", "Mercury"), 3);
            Question qu14 = new Question(q5, "What is the chemical symbol for gold?", Arrays.asList("G", "Au", "Go"), 2);
            Question qu15 = new Question(q5, "What is the third most common gas found in the air we breathe?", Arrays.asList("Oxygen", "Nitrogen", "Argon"), 3);
            questionRepository.saveAll(Arrays.asList(qu1, qu2, qu3, qu4, qu5, qu6, qu7, qu8, qu9, qu10, qu11, qu12, qu13, qu14, qu15));

            //answers
            HashMap<Long, Integer> a1 = new HashMap<>();
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 1);

            HashMap<Long, Integer> a2 = new HashMap<>();
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 2);
            a1.put(q1.getId(), 3);

            HashMap<Long, Integer> a3 = new HashMap<>();
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 2);
            a1.put(q1.getId(), 1);

            HashMap<Long, Integer> a4 = new HashMap<>();
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 3);

            HashMap<Long, Integer> a5 = new HashMap<>();
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 2);
            a1.put(q1.getId(), 3);

            HashMap<Long, Integer> a6 = new HashMap<>();
            a1.put(q1.getId(), 1);
            a1.put(q1.getId(), 2);
            a1.put(q1.getId(), 3);

            Submission s1 = new Submission(q1, u1, a1, q1.calculateScore(a1)); //q1 - c1: u1&u2
            Submission s2 = new Submission(q1, u2, a2, q1.calculateScore(a2));
            Submission s3 = new Submission(q2, u2, a1, q1.calculateScore(a1)); //q2 - c3: u2
            Submission s4 = new Submission(q3, u1, a3, q1.calculateScore(a1)); //q3 - c2: u1 & u3
            Submission s5 = new Submission(q3, u3, a4, q1.calculateScore(a1));
            Submission s6 = new Submission(q4, u1, a1, q1.calculateScore(a1)); //q4 - c1: u1&u2
            submissionRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));
        };
    }

    private void associateUserWithCourse(User user, Course course) {
        course.getUsers().add(user);
        user.getCourse().add(course);
    }
}
