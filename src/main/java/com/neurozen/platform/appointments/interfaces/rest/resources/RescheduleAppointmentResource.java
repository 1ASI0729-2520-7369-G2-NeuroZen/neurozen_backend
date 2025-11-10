package com.neurozen.platform.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Reschedule appointment resource.
 */
public record RescheduleAppointmentResource(LocalDateTime newDateTime) {
}

