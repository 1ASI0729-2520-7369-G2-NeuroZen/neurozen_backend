package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.commands.UpdateAppointmentStatusCommand;
import com.example.neurozen_platform.appointment.domain.model.valueobjects.AppointmentStatus;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.UpdateAppointmentStatusResource;

public class UpdateAppointmentStatusCommandFromResourceAssembler {
  public static UpdateAppointmentStatusCommand toCommandFromResource(Long appointmentId, UpdateAppointmentStatusResource resource) {
    var status = AppointmentStatus.valueOf(resource.status().toUpperCase());
    return new UpdateAppointmentStatusCommand(appointmentId, status);
  }
}

