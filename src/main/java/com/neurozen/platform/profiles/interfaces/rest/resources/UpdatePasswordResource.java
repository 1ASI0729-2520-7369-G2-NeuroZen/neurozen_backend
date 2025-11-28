package com.neurozen.platform.profiles.interfaces.rest.resources;

/**
 * Update password resource.
 */
public record UpdatePasswordResource(
        String currentPassword,
        String newPassword
) {
}

