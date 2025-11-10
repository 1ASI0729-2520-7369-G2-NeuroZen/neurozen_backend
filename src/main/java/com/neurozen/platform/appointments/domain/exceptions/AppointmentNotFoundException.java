package com.neurozen.platform.appointments.domain.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(Long appointmentId) {
        super("Appointment with id %s not found".formatted(appointmentId));
    }
}

