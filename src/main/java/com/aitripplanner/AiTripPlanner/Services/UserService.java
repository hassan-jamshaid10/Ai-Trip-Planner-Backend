package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Authentication.Request.RegisterRequest;
import com.aitripplanner.AiTripPlanner.Entites.User;
import com.aitripplanner.AiTripPlanner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user) {
        // Hash the password before saving
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(Integer id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id); // Ensure the ID is correctly set
            // Hash the password if it's being updated
            if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
                user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
            }
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    @Transactional
    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    public void registerNewUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .passwordHash(passwordEncoder.encode(registerRequest.getPassword())) // Encode password
                .build();
        createUser(user);
    }
}
