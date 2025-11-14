package com.example.neurozen_platform.profile.interfaces.rest.resources;

import java.util.List;

public record CreatePsychologistProfileResource(
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

