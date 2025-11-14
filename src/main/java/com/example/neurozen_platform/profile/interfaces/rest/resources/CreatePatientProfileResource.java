package com.example.neurozen_platform.profile.interfaces.rest.resources;

public record CreatePatientProfileResource(
  Long userId,
  String firstName,
  String lastName,
  String email,
  String phoneNumber,
  String district
) {
}

