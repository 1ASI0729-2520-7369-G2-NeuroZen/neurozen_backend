package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.commands.CreateAppointmentCommand;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
  public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
    return new CreateAppointmentCommand(
      resource.patientId(),
      resource.professionalId(),
      resource.appointmentDateTime(),
      resource.type(),
      resource.mode(),
      resource.reason()
    );
  }
}

