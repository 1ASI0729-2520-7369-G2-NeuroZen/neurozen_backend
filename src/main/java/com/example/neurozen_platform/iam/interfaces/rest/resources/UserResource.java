package com.example.neurozen_platform.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(
  Long id,
  String username,
  String email,
  String firstName,
  String lastName,
  List<String> roles
) {
}

