package com.example.neurozen_platform.profile.interfaces.rest.resources;

public record UpdateProfileResource(
  String firstName,
  String lastName,
  String phoneNumber,
  String district
) {
}

