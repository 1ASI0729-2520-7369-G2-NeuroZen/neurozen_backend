package com.example.neurozen_platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(
  Long id,
  String username,
  String email,
  String firstName,
  String lastName,
  String token
) {
}

