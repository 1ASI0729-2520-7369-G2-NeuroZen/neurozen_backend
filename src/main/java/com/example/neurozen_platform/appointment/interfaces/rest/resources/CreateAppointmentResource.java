package com.example.neurozen_platform.appointment.interfaces.rest.resources;

public record CreateAppointmentResource(
  Long patientId,
  Long professionalId,
  String appointmentDateTime,
  String type,
  String mode,
  String reason
) {
}

