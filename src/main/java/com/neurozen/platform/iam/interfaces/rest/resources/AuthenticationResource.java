package com.neurozen.platform.iam.interfaces.rest.resources;

/**
 * Authentication resource.
 */
public record AuthenticationResource(
                Long id,
                String email,
                String name,
                String token,
                String role) {
}
