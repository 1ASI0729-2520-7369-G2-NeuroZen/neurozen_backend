package com.example.neurozen_platform.appointment.interfaces.rest.resources;

public record AppointmentResource(
  Long id,
  Long patientId,
  Long professionalId,
  String appointmentDateTime,
  String status,
  String type,
  String mode,
  String reason
) {
}

