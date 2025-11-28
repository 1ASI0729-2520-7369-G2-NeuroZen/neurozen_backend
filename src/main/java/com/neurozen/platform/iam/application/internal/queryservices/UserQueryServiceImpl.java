package com.neurozen.platform.iam.application.internal.queryservices;

import com.neurozen.platform.iam.domain.model.aggregates.User;
import com.neurozen.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User query service implementation.
 */
@Service
public class UserQueryServiceImpl {
    
    private final UserRepository userRepository;
    
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Get user by ID
     * @param userId The user ID
     * @return The user if found
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    /**
     * Get user by email
     * @param email The user email
     * @return The user if found
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

