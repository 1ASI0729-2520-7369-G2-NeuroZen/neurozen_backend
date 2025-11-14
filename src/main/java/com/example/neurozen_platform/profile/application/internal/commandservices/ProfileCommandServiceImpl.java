package com.example.neurozen_platform.profile.application.internal.commandservices;

import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.domain.model.commands.CreatePatientProfileCommand;
import com.example.neurozen_platform.profile.domain.model.commands.CreatePsychologistProfileCommand;
import com.example.neurozen_platform.profile.domain.model.commands.UpdateProfileCommand;
import com.example.neurozen_platform.profile.domain.model.valueobjects.ProfileType;
import com.example.neurozen_platform.profile.domain.services.ProfileCommandService;
import com.example.neurozen_platform.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

  @Autowired
  private ProfileRepository profileRepository;

  @Override
  public Optional<Profile> handle(CreatePatientProfileCommand command) {
    // Check if profile already exists for this user
    if (profileRepository.existsByUserId(command.userId())) {
      throw new IllegalArgumentException("Profile already exists for user ID: " + command.userId());
    }

    if (profileRepository.existsByEmail(command.email())) {
      throw new IllegalArgumentException("Profile already exists with email: " + command.email());
    }

    var profile = new Profile(
      command.userId(),
      command.firstName(),
      command.lastName(),
      command.email(),
      command.phoneNumber(),
      command.district(),
      ProfileType.PATIENT
    );

    profileRepository.save(profile);
    return Optional.of(profile);
  }

  @Override
  public Optional<Profile> handle(CreatePsychologistProfileCommand command) {
    // Check if profile already exists for this user
    if (profileRepository.existsByUserId(command.userId())) {
      throw new IllegalArgumentException("Profile already exists for user ID: " + command.userId());
    }

    if (profileRepository.existsByEmail(command.email())) {
      throw new IllegalArgumentException("Profile already exists with email: " + command.email());
    }

    var profile = new Profile(
      command.userId(),
      command.firstName(),
      command.lastName(),
      command.email(),
      command.phoneNumber(),
      command.specialty(),
      command.license(),
      command.about(),
      command.experience(),
      command.languages(),
      command.specialties(),
      command.price(),
      command.imageUrl()
    );

    profileRepository.save(profile);
    return Optional.of(profile);
  }

  @Override
  public Optional<Profile> handle(UpdateProfileCommand command) {
    var profileOptional = profileRepository.findById(command.profileId());
    
    if (profileOptional.isEmpty()) {
      throw new IllegalArgumentException("Profile not found with ID: " + command.profileId());
    }

    var profile = profileOptional.get();
    profile.updateBasicInfo(
      command.firstName(),
      command.lastName(),
      command.phoneNumber(),
      command.district()
    );

    profileRepository.save(profile);
    return Optional.of(profile);
  }
}

