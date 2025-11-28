package com.neurozen.platform.profiles.interfaces.rest.resources;

/**
 * Profile resource.
 */
public record ProfileResource(
        Long id,
        String email,
        String name,
        String phone,
        String district,
        String bio
) {
}

