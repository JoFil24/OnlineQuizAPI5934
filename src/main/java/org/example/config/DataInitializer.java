package org.example.config;

import org.example.model.Submission;
import org.example.model.User;
import org.example.repository.QuestionRepository;
import org.example.repository.QuizRepository;
import org.example.repository.SubmissionRepository;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(
            QuestionRepository questionRepository,
            QuizRepository quizRepository,
            SubmissionRepository submissionRepository,
            UserRepository userRepository) {
        return args -> {
            User u1 = new User("John");
            User u2 = new User("James");
            User u3 = new User("Katerina");
            userRepository.saveAll(Arrays.asList(u1, u2, u3));
        };
    }
}
