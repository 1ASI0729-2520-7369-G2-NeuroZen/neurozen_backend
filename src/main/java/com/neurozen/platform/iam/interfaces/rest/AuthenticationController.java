package com.neurozen.platform.iam.interfaces.rest;

import com.neurozen.platform.iam.application.internal.commandservices.UserCommandServiceImpl;
import com.neurozen.platform.iam.application.internal.queryservices.UserQueryServiceImpl;
import com.neurozen.platform.iam.domain.model.aggregates.User;
import com.neurozen.platform.iam.interfaces.rest.resources.AuthenticationResource;
import com.neurozen.platform.iam.interfaces.rest.resources.LoginResource;
import com.neurozen.platform.iam.interfaces.rest.resources.RegisterResource;
import com.neurozen.platform.iam.interfaces.rest.resources.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Authentication controller.
 * 
 * @summary
 *          This controller provides endpoints for user authentication and
 *          registration.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Available Authentication Endpoints")
public class AuthenticationController {

    private final UserCommandServiceImpl userCommandService;
    private final UserQueryServiceImpl userQueryService;

    public AuthenticationController(UserCommandServiceImpl userCommandService, UserQueryServiceImpl userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Register a new user
     * 
     * @param resource The register resource
     * @return The authentication resource with user details and token
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterResource resource) {
        try {
            User user = userCommandService.register(resource.email(), resource.name(), resource.password());

            // Generate a simple token (in production, use JWT)
            String token = UUID.randomUUID().toString();

            AuthenticationResource authResource = new AuthenticationResource(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    token);

            return ResponseEntity.status(HttpStatus.CREATED).body(authResource);
        } catch (IllegalArgumentException e) {
            // Return error message in response body
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // Simple error response record
    private record ErrorResponse(String message) {
    }

    /**
     * Login a user
     * 
     * @param resource The login resource
     * @return The authentication resource with user details and token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResource> login(@RequestBody LoginResource resource) {
        Optional<User> userOpt = userCommandService.authenticate(resource.email(), resource.password());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Generate a simple token (in production, use JWT)
            String token = UUID.randomUUID().toString();

            AuthenticationResource authResource = new AuthenticationResource(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    token);

            return ResponseEntity.ok(authResource);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Get current user (mock endpoint - in production would validate token)
     * 
     * @param userId The user ID
     * @return The user resource
     */
    @GetMapping("/me/{userId}")
    public ResponseEntity<UserResource> getCurrentUser(@PathVariable Long userId) {
        Optional<User> userOpt = userQueryService.getUserById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserResource userResource = new UserResource(
                    user.getId(),
                    user.getEmail(),
                    user.getName(),
                    user.getPhone(),
                    user.getDistrict(),
                    user.getBio());
            return ResponseEntity.ok(userResource);
        }

        return ResponseEntity.notFound().build();
    }
}
