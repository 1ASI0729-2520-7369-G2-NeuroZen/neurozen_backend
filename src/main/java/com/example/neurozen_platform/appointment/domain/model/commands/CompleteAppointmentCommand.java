package com.example.neurozen_platform.appointment.domain.model.commands;

public record CompleteAppointmentCommand(Long appointmentId) {
    public CompleteAppointmentCommand {
        if (appointmentId == null || appointmentId <= 0) {
            throw new IllegalArgumentException("Invalid appointment ID");
        }
    }

}
