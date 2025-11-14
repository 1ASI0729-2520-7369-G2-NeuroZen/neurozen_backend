package com.example.neurozen_platform.appointment.domain.model.commands;

import com.example.neurozen_platform.appointment.domain.model.valueobjects.AppointmentStatus;

public record UpdateAppointmentStatusCommand(
  Long appointmentId,
  AppointmentStatus status
) {
}

