package com.example.neurozen_platform.profile.interfaces.rest.transform;

import com.example.neurozen_platform.profile.domain.model.commands.CreatePatientProfileCommand;
import com.example.neurozen_platform.profile.interfaces.rest.resources.CreatePatientProfileResource;

public class CreatePatientProfileCommandFromResourceAssembler {
  public static CreatePatientProfileCommand toCommandFromResource(CreatePatientProfileResource resource) {
    return new CreatePatientProfileCommand(
      resource.userId(),
      resource.firstName(),
      resource.lastName(),
      resource.email(),
      resource.phoneNumber(),
      resource.district()
    );
  }
}

