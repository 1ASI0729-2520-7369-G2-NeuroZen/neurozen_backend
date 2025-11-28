package com.neurozen.platform.profiles.interfaces.rest.resources;

/**
 * Update profile resource.
 */
public record UpdateProfileResource(
        String name,
        String phone,
        String district,
        String bio
) {
}

