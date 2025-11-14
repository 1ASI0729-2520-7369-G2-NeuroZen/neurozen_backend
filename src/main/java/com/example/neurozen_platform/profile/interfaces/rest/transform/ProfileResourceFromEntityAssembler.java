package com.example.neurozen_platform.profile.interfaces.rest.transform;

import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
  public static ProfileResource toResourceFromEntity(Profile entity) {
    return new ProfileResource(
      entity.getId(),
      entity.getUserId(),
      entity.getFirstName(),
      entity.getLastName(),
      entity.getEmail(),
      entity.getPhoneNumber(),
      entity.getDistrict(),
      entity.getProfileType().name(),
      entity.getSpecialty(),
      entity.getLicense(),
      entity.getAbout(),
      entity.getExperience(),
      entity.getLanguages(),
      entity.getSpecialties(),
      entity.getRating(),
      entity.getReviewCount(),
      entity.getPrice(),
      entity.getImageUrl(),
      entity.getNextAvailable()
    );
  }
}

