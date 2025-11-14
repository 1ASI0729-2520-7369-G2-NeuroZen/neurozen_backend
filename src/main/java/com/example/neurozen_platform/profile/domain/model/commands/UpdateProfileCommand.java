package com.example.neurozen_platform.profile.domain.model.commands;

public record UpdateProfileCommand(
  Long profileId,
  String firstName,
  String lastName,
  String phoneNumber,
  String district
) {
}

