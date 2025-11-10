package com.neurozen.platform.appointments.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

/**
 * Appointment date and time value object.
 * @summary
 * This value object represents the scheduled date and time for an appointment.
 */
@Embeddable
public record AppointmentDateTime(@Column(name = "appointment_date_time") LocalDateTime dateTime) {
    public AppointmentDateTime {
        if (dateTime == null) {
            throw new IllegalArgumentException("Appointment date and time cannot be null");
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date and time cannot be in the past");
        }
    }
}

