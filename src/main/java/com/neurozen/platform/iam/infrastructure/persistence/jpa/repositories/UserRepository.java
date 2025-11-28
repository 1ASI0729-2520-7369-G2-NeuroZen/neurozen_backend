package com.neurozen.platform.iam.infrastructure.persistence.jpa.repositories;

import com.neurozen.platform.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by email
     * @param email The user email
     * @return The user if found
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if user exists by email
     * @param email The user email
     * @return True if user exists
     */
    boolean existsByEmail(String email);
}

