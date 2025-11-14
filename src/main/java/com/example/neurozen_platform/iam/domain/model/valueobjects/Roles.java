package com.example.neurozen_platform.iam.domain.model.valueobjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Roles {
  ROLE_USER,
  ROLE_PSYCHOLOGIST,
  ROLE_ADMIN;

  public static Set<Roles> validateRoleSet(List<Roles> roles) {
    if (roles == null || roles.isEmpty()) {
      return new HashSet<>(Arrays.asList(ROLE_USER));
    }
    return new HashSet<>(roles);
  }

  public String getName() {
    return this.name();
  }
}

