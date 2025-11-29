package com.neurozen.platform.iam.domain.model.aggregates;

import com.neurozen.platform.iam.domain.model.valueobjects.UserRole;
import com.neurozen.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * User aggregate root.
 * 
 * @summary
 *          This aggregate represents a user in the system with authentication
 *          credentials.
 */
@Entity
@Table(name = "users")
@Getter
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String district;

    @Column(length = 1000)
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole role;

    public User() {
        super();
        this.role = UserRole.EMPLOYEE; // Default role
    }

    /**
     * Create a new user
     *
     * @param email    The user email
     * @param name     The user name
     * @param password The user password (should be hashed)
     */
    public User(String email, String name, String password) {
        this();
        this.email = email;
        this.name = name;
        this.password = password;
    }

    /**
     * Create a new user with role
     * 
     * @param email    The user email
     * @param name     The user name
     * @param password The user password (should be hashed)
     * @param role     The user role
     */
    public User(String email, String name, String password, UserRole role) {
        this();
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    /**
     * Update user profile
     * 
     * @param name     The user name
     * @param phone    The user phone
     * @param district The user district
     * @param bio      The user bio
     */
    public void updateProfile(String name, String phone, String district, String bio) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
        this.phone = phone;
        this.district = district;
        this.bio = bio;
    }

    /**
     * Update user password
     * 
     * @param newPassword The new password (should be hashed)
     */
    public void updatePassword(String newPassword) {
        if (newPassword == null || newPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.password = newPassword;
    }

    /**
     * Update user email
     * 
     * @param newEmail The new email
     */
    public void updateEmail(String newEmail) {
        if (newEmail == null || newEmail.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        this.email = newEmail;
    }
}
