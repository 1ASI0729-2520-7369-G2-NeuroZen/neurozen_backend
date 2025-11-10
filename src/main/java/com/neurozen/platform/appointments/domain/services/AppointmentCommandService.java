package com.neurozen.platform.appointments.domain.services;

import com.neurozen.platform.appointments.domain.model.aggregates.Appointment;
import com.neurozen.platform.appointments.domain.model.commands.*;

import java.util.Optional;

/**
 * AppointmentCommandService
 * Service that handles appointment commands
 */
public interface AppointmentCommandService {
    /**
     * Handle a create appointment command
     * @param command The create appointment command
     * @return The created appointment ID
     */
    Long handle(CreateAppointmentCommand command);
    
    /**
     * Handle a confirm appointment command
     * @param command The confirm appointment command
     * @return The confirmed appointment
     */
    Optional<Appointment> handle(ConfirmAppointmentCommand command);
    
    /**
     * Handle a start appointment command
     * @param command The start appointment command
     * @return The started appointment
     */
    Optional<Appointment> handle(StartAppointmentCommand command);
    
    /**
     * Handle a complete appointment command
     * @param command The complete appointment command
     * @return The completed appointment
     */
    Optional<Appointment> handle(CompleteAppointmentCommand command);
    
    /**
     * Handle a cancel appointment command
     * @param command The cancel appointment command
     */
    void handle(CancelAppointmentCommand command);
    
    /**
     * Handle a reschedule appointment command
     * @param command The reschedule appointment command
     * @return The rescheduled appointment
     */
    Optional<Appointment> handle(RescheduleAppointmentCommand command);
}

