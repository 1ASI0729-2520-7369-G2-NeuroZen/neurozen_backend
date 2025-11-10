package com.neurozen.platform.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Appointment resource.
 */
public record AppointmentResource(
        Long id,
        Long employeeId,
        Long psychologistId,
        LocalDateTime appointmentDateTime,
        String status,
        String notes,
        String cancellationReason
) {
}

