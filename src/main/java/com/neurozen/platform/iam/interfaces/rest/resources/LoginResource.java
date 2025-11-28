package com.neurozen.platform.iam.interfaces.rest.resources;

/**
 * Login resource.
 */
public record LoginResource(
        String email,
        String password
) {
}

