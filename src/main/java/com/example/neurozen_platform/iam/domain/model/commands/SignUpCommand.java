package com.example.neurozen_platform.iam.domain.model.commands;

import com.example.neurozen_platform.iam.domain.model.valueobjects.Roles;

import java.util.List;

public record SignUpCommand(
  String username,
  String email,
  String password,
  String firstName,
  String lastName,
  List<Roles> roles
) {
  public SignUpCommand {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username cannot be null or blank");
    }
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email cannot be null or blank");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("Password cannot be null or blank");
    }
    if (firstName == null || firstName.isBlank()) {
      throw new IllegalArgumentException("First name cannot be null or blank");
    }
    if (lastName == null || lastName.isBlank()) {
      throw new IllegalArgumentException("Last name cannot be null or blank");
    }
  }
}

