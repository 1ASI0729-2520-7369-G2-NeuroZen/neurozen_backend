package com.example.neurozen_platform.profile.domain.services;

import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.domain.model.queries.GetAllPsychologistsQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetProfileByIdQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.example.neurozen_platform.profile.domain.model.queries.GetPsychologistsBySpecialtyQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
  Optional<Profile> handle(GetProfileByIdQuery query);
  Optional<Profile> handle(GetProfileByUserIdQuery query);
  List<Profile> handle(GetAllPsychologistsQuery query);
  List<Profile> handle(GetPsychologistsBySpecialtyQuery query);
}

