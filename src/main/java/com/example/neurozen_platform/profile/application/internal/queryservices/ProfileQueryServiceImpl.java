package com.example.neurozen_platform.profile.application.internal.queryservices;

import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.domain.model.queries.GetAllPsychologistsQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetProfileByIdQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetPsychologistsBySpecialtyQuery;
import com.example.neurozen_platform.profile.domain.model.valueobjects.ProfileType;
import com.example.neurozen_platform.profile.domain.services.ProfileQueryService;
import com.example.neurozen_platform.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

  @Autowired
  private ProfileRepository profileRepository;

  @Override
  public Optional<Profile> handle(GetProfileByIdQuery query) {
    return profileRepository.findById(query.profileId());
  }

  @Override
  public Optional<Profile> handle(GetProfileByUserIdQuery query) {
    return profileRepository.findByUserId(query.userId());
  }

  @Override
  public List<Profile> handle(GetAllPsychologistsQuery query) {
    return profileRepository.findByProfileType(ProfileType.PSYCHOLOGIST);
  }

  @Override
  public List<Profile> handle(GetPsychologistsBySpecialtyQuery query) {
    return profileRepository.findByProfileTypeAndSpecialtyContainingIgnoreCase(
      ProfileType.PSYCHOLOGIST,
      query.specialty()
    );
  }
}

