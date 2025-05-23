package org.example.service;

import org.example.model.User;
import org.example.repository.QuizRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public User findByCourseId(Long courseId){
        return userRepository.findByCourseId(courseId);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Couldn't find user"));
    }

    public User createUser (User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long userId, User userDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setName(userDetails.getName());

            return userRepository.save(user);
        });
    }

    public void deleteUser (Long userId) {
        userRepository.deleteById(userId);
    }
}
