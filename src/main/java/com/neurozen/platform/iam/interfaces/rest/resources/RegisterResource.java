package com.neurozen.platform.iam.interfaces.rest.resources;

/**
 * Register resource.
 */
public record RegisterResource(
                String name,
                String email,
                String password,
                String role) {
}
