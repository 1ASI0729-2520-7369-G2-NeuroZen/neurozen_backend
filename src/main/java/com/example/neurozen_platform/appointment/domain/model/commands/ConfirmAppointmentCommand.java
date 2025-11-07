package com.example.neurozen_platform.appointment.domain.model.commands;

public record ConfirmAppointmentCommand(Long appointmentId) {
    public ConfirmAppointmentCommand {
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("Invalid appointment ID");
        }
    }
}
