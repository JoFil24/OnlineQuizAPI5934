package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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
