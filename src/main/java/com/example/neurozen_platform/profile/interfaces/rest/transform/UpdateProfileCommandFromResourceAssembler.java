package com.example.neurozen_platform.profile.interfaces.rest.transform;

import com.example.neurozen_platform.profile.domain.model.commands.UpdateProfileCommand;
import com.example.neurozen_platform.profile.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
  public static UpdateProfileCommand toCommandFromResource(Long profileId, UpdateProfileResource resource) {
    return new UpdateProfileCommand(
      profileId,
      resource.firstName(),
      resource.lastName(),
      resource.phoneNumber(),
      resource.district()
    );
  }
}

