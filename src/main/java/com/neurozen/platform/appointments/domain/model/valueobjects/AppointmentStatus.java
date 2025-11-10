package com.neurozen.platform.appointments.domain.model.valueobjects;

/**
 * Appointment status value object.
 * @summary
 * The class is a value object in the Appointments bounded context.
 * The possible appointment statuses are: SCHEDULED, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED, NO_SHOW.
 */
public enum AppointmentStatus {
    SCHEDULED,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    NO_SHOW
}

