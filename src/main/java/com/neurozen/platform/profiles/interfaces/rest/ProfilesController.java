package com.neurozen.platform.profiles.interfaces.rest;

import com.neurozen.platform.iam.application.internal.commandservices.UserCommandServiceImpl;
import com.neurozen.platform.iam.application.internal.queryservices.UserQueryServiceImpl;
import com.neurozen.platform.iam.domain.model.aggregates.User;
import com.neurozen.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.neurozen.platform.profiles.interfaces.rest.resources.UpdatePasswordResource;
import com.neurozen.platform.profiles.interfaces.rest.resources.UpdateProfileResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Profiles controller.
 * @summary
 * This controller provides endpoints for user profile management.
 */
@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Available Profile Endpoints")
public class ProfilesController {
    
    private final UserCommandServiceImpl userCommandService;
    private final UserQueryServiceImpl userQueryService;
    
    public ProfilesController(UserCommandServiceImpl userCommandService, UserQueryServiceImpl userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }
    
    /**
     * Get user profile by ID
     * @param userId The user ID
     * @return The profile resource
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResource> getProfile(@PathVariable Long userId) {
        Optional<User> userOpt = userQueryService.getUserById(userId);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            ProfileResource profileResource = new ProfileResource(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    user.getPhone(),
                    user.getDistrict(),
                    user.getBio()
            );
            return ResponseEntity.ok(profileResource);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Update user profile
     * @param userId The user ID
     * @param resource The update profile resource
     * @return The updated profile resource
     */
    @PutMapping("/{userId}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long userId, @RequestBody UpdateProfileResource resource) {
        Optional<User> userOpt = userCommandService.updateProfile(
                userId,
                resource.name(),
                resource.phone(),
                resource.district(),
                resource.bio()
        );
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            ProfileResource profileResource = new ProfileResource(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    user.getPhone(),
                    user.getDistrict(),
                    user.getBio()
            );
            return ResponseEntity.ok(profileResource);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Update user password
     * @param userId The user ID
     * @param resource The update password resource
     * @return No content if successful
     */
    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody UpdatePasswordResource resource) {
        try {
            Optional<User> userOpt = userCommandService.updatePassword(
                    userId,
                    resource.currentPassword(),
                    resource.newPassword()
            );
            
            if (userOpt.isPresent()) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Delete user profile
     * @param userId The user ID
     * @return No content if successful
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        userCommandService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

