package com.example.neurozen_platform.profile.domain.model.commands;

public record CreatePatientProfileCommand(
  Long userId,
  String firstName,
  String lastName,
  String email,
  String phoneNumber,
  String district
) {
}

