package com.neurozen.platform.appointments.domain.model.commands;

import com.neurozen.platform.appointments.domain.model.valueobjects.AppointmentDateTime;

public record RescheduleAppointmentCommand(Long appointmentId, AppointmentDateTime newDateTime) {
}

