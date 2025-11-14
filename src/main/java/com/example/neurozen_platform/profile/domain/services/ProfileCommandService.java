package com.example.neurozen_platform.profile.domain.services;

import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.domain.model.commands.CreatePatientProfileCommand;
import com.example.neurozen_platform.profile.domain.model.commands.CreatePsychologistProfileCommand;
import com.example.neurozen_platform.profile.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
  Optional<Profile> handle(CreatePatientProfileCommand command);
  Optional<Profile> handle(CreatePsychologistProfileCommand command);
  Optional<Profile> handle(UpdateProfileCommand command);
}

