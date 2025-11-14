package com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
  List<MedicalAppointment> findByPatientId(Long patientId);
  List<MedicalAppointment> findByProfessionalId(Long professionalId);
}

