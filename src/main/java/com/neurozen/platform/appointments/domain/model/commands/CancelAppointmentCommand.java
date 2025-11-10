package com.neurozen.platform.appointments.domain.model.commands;

public record CancelAppointmentCommand(Long appointmentId, String reason) {
}

