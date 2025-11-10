package com.neurozen.platform.appointments.infrastructure.persistence.jpa.repositories;

import com.neurozen.platform.appointments.domain.model.aggregates.Appointment;
import com.neurozen.platform.appointments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.appointments.domain.model.valueobjects.PsychologistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Appointment repository
 * <p>This interface is used to interact with the database and perform CRUD and business command and query supporting operations on the Appointment entity.</p>
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    /**
     * Find all appointments by employee ID
     * @param employeeId The employee ID
     * @return List of appointments for the employee
     */
    List<Appointment> findByEmployeeId(EmployeeId employeeId);
    
    /**
     * Find all appointments by psychologist ID
     * @param psychologistId The psychologist ID
     * @return List of appointments for the psychologist
     */
    List<Appointment> findByPsychologistId(PsychologistId psychologistId);
}

