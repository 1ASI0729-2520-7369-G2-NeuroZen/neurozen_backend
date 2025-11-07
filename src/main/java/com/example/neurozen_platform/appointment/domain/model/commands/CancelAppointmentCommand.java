package com.example.neurozen_platform.appointment.domain.model.commands;

public record CancelAppointmentCommand(Long appointmentId) {
    public CancelAppointmentCommand {
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("Invalid appointment ID");
        }
    }

}
