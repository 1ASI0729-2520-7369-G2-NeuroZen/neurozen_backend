package com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    //This method is used to find all appointments by professionalId
    List<MedicalAppointment> findByProfessionalId(Long professionalId);

    //This method is used to find all appointments by patientId
    List<MedicalAppointment> findByPatientId(Long patientId);

    Optional<MedicalAppointment> findByPatientIdAndProfessionalId(Long patientId, Long professionalId);

}
