package com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.neurozen_platform.appointment.domain.model.aggregates.Professional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    
}
