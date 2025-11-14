package com.example.neurozen_platform.appointment.domain.model.commands;

public record CreateAppointmentCommand(
  Long patientId,
  Long professionalId,
  String appointmentDateTime,
  String type,
  String mode,
  String reason
) {
}

