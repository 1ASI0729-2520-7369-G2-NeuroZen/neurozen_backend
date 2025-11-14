package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
  public static AppointmentResource toResourceFromEntity(MedicalAppointment entity) {
    return new AppointmentResource(
      entity.getId(),
      entity.getPatient().getId(),
      entity.getProfessional().getId(),
      entity.getAppointmentDateTime(),
      entity.getStatus(),
      entity.getType(),
      entity.getMode(),
      entity.getReason()
    );
  }
}

