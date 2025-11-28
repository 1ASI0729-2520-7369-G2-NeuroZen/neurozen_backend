package com.neurozen.platform.iam.interfaces.rest.resources;

/**
 * User resource.
 */
public record UserResource(
        Long id,
        String email,
        String name,
        String phone,
        String district,
        String bio
) {
}

