package com.example.neurozen_platform.iam.interfaces.rest.resources;

import com.example.neurozen_platform.iam.domain.model.valueobjects.Roles;

import java.util.List;

public record SignUpResource(
  String username,
  String email,
  String password,
  String firstName,
  String lastName,
  List<Roles> roles
) {
}

