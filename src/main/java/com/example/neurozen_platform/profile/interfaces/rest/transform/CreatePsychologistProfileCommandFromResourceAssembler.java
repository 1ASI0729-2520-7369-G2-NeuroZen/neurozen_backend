package com.example.neurozen_platform.profile.interfaces.rest.transform;

import com.example.neurozen_platform.profile.domain.model.commands.CreatePsychologistProfileCommand;
import com.example.neurozen_platform.profile.interfaces.rest.resources.CreatePsychologistProfileResource;

public class CreatePsychologistProfileCommandFromResourceAssembler {
  public static CreatePsychologistProfileCommand toCommandFromResource(CreatePsychologistProfileResource resource) {
    return new CreatePsychologistProfileCommand(
      resource.userId(),
      resource.firstName(),
      resource.lastName(),
      resource.email(),
      resource.phoneNumber(),
      resource.specialty(),
      resource.license(),
      resource.about(),
      resource.experience(),
      resource.languages(),
      resource.specialties(),
      resource.price(),
      resource.imageUrl()
    );
  }
}

