package com.example.neurozen_platform.iam.interfaces.rest.transform;

import com.example.neurozen_platform.iam.domain.model.commands.SignUpCommand;
import com.example.neurozen_platform.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
  public static SignUpCommand toCommandFromResource(SignUpResource resource) {
    return new SignUpCommand(
      resource.username(),
      resource.email(),
      resource.password(),
      resource.firstName(),
      resource.lastName(),
      resource.roles()
    );
  }
}

