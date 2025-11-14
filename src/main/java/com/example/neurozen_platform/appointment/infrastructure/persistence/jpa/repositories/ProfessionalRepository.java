package com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories;

import com.example.neurozen_platform.appointment.domain.model.aggregates.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
  Optional<Professional> findByProfileId(Long profileId);
}

