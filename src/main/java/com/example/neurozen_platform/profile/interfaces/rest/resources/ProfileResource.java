package com.example.neurozen_platform.profile.interfaces.rest.resources;

import java.util.List;

public record ProfileResource(
  Long id,
  Long userId,
  String firstName,
  String lastName,
  String email,
  String phoneNumber,
  String district,
  String profileType,
  String specialty,
  String license,
  String about,
  Integer experience,
  List<String> languages,
  List<String> specialties,
  Double rating,
  Integer reviewCount,
  Double price,
  String imageUrl,
  String nextAvailable
) {
}

