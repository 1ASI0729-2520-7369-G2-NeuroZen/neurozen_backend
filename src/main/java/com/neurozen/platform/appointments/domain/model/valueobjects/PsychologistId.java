package com.neurozen.platform.appointments.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Psychologist ID value object.
 * @summary
 * This value object represents a psychologist identifier in the Appointments bounded context.
 */
@Embeddable
public record PsychologistId(@Column(name = "psychologist_id") Long psychologistId) {
    public PsychologistId {
        if (psychologistId == null || psychologistId <= 0) {
            throw new IllegalArgumentException("Psychologist ID must be a positive number");
        }
    }
}

