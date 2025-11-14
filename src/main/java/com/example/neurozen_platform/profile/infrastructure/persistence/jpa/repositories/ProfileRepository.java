package com.example.neurozen_platform.profile.infrastructure.persistence.jpa.repositories;

import com.example.neurozen_platform.profile.domain.model.aggregates.Profile;
import com.example.neurozen_platform.profile.domain.model.valueobjects.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
  Optional<Profile> findByUserId(Long userId);
  Optional<Profile> findByEmail(String email);
  List<Profile> findByProfileType(ProfileType profileType);
  List<Profile> findByProfileTypeAndSpecialtyContainingIgnoreCase(ProfileType profileType, String specialty);
  boolean existsByEmail(String email);
  boolean existsByUserId(Long userId);
}

