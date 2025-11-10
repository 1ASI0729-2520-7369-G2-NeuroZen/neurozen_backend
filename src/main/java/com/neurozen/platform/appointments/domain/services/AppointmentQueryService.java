package com.neurozen.platform.appointments.domain.services;

import com.neurozen.platform.appointments.domain.model.aggregates.Appointment;
import com.neurozen.platform.appointments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * AppointmentQueryService
 * Service that handles appointment queries
 */
public interface AppointmentQueryService {
    /**
     * Handle a get appointment by id query
     * @param query The get appointment by id query
     * @return The appointment if found
     */
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    
    /**
     * Handle a get all appointments query
     * @param query The get all appointments query
     * @return The list of appointments
     */
    List<Appointment> handle(GetAllAppointmentsQuery query);
    
    /**
     * Handle a get all appointments by employee id query
     * @param query The get all appointments by employee id query
     * @return The list of appointments for the employee
     */
    List<Appointment> handle(GetAllAppointmentsByEmployeeIdQuery query);
    
    /**
     * Handle a get all appointments by psychologist id query
     * @param query The get all appointments by psychologist id query
     * @return The list of appointments for the psychologist
     */
    List<Appointment> handle(GetAllAppointmentsByPsychologistIdQuery query);
}

