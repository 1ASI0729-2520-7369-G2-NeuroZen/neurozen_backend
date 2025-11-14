package com.example.neurozen_platform.profile.domain.model.commands;

import java.util.List;

public record CreatePsychologistProfileCommand(
  Long userId,
  String firstName,
  String lastName,
  String email,
  String phoneNumber,
  String specialty,
  String license,
  String about,
  Integer experience,
  List<String> languages,
  List<String> specialties,
  Double price,
  String imageUrl
) {
}

