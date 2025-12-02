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

    /**
     * Find an appointment by its ID
     * @param id The appointment ID
     * @return Optional containing the appointment if found, otherwise empty
     */
    Optional<Appointment> findById(Long id);

    /**
     * Check if an appointment exists by employee ID and psychologist ID
     * @param employeeId The employee ID
     * @param psychologistId The psychologist ID
     * @return true if an appointment exists, false otherwise
     */
    boolean existsByEmployeeIdAndPsychologistId(EmployeeId employeeId, PsychologistId psychologistId);
}
