package com.neurozen.platform.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Create appointment resource.
 */
public record CreateAppointmentResource(
        Long employeeId,
        Long psychologistId,
        LocalDateTime appointmentDateTime
) {
}

