package com.neurozen.platform.iam.application.internal.commandservices;

import com.neurozen.platform.iam.domain.model.aggregates.User;
import com.neurozen.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation.
 */
@Service
public class UserCommandServiceImpl {
    
    private final UserRepository userRepository;
    
    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Register a new user
     * @param email The user email
     * @param name The user name
     * @param password The user password (plain text - will be stored as-is for now)
     * @return The created user
     */
    public User register(String email, String name, String password) {
        // Check if user already exists
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        
        // Create new user (password should be hashed in production)
        User user = new User(email, name, password);
        return userRepository.save(user);
    }
    
    /**
     * Authenticate a user
     * @param email The user email
     * @param password The user password
     * @return The authenticated user if credentials are valid
     */
    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Simple password check (should use password hashing in production)
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Update user profile
     * @param userId The user ID
     * @param name The user name
     * @param phone The user phone
     * @param district The user district
     * @param bio The user bio
     * @return The updated user
     */
    public Optional<User> updateProfile(Long userId, String name, String phone, String district, String bio) {
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.updateProfile(name, phone, district, bio);
            return Optional.of(userRepository.save(user));
        }
        
        return Optional.empty();
    }
    
    /**
     * Update user password
     * @param userId The user ID
     * @param currentPassword The current password
     * @param newPassword The new password
     * @return The updated user
     */
    public Optional<User> updatePassword(Long userId, String currentPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            
            // Verify current password
            if (!user.getPassword().equals(currentPassword)) {
                throw new IllegalArgumentException("Current password is incorrect");
            }
            
            user.updatePassword(newPassword);
            return Optional.of(userRepository.save(user));
        }
        
        return Optional.empty();
    }
    
    /**
     * Delete user
     * @param userId The user ID
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

