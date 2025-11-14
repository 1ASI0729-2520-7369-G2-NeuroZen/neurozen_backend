package com.example.neurozen_platform.iam.interfaces.rest.transform;

import com.example.neurozen_platform.iam.domain.model.aggregates.User;
import com.example.neurozen_platform.iam.interfaces.rest.resources.UserResource;

import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler {
  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
      .map(role -> role.name())
      .collect(Collectors.toList());

    return new UserResource(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getFirstName(),
      user.getLastName(),
      roles
    );
  }
}

