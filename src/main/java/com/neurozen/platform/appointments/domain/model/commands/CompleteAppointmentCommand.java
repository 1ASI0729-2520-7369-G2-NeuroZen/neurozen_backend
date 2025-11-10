package com.neurozen.platform.appointments.domain.model.commands;

public record CompleteAppointmentCommand(Long appointmentId, String notes) {
}

