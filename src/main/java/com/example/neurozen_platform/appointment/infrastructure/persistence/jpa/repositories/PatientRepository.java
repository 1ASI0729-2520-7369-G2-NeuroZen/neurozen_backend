package com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories;

import com.example.neurozen_platform.appointment.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  Optional<Patient> findByProfileId(Long profileId);
}

