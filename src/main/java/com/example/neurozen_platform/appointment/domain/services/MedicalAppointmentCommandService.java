package com.example.neurozen_platform.appointment.domain.services;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.commands.CreateAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.UpdateAppointmentStatusCommand;

import java.util.Optional;

public interface MedicalAppointmentCommandService {
  Optional<MedicalAppointment> handle(CreateAppointmentCommand command);
  Optional<MedicalAppointment> handle(UpdateAppointmentStatusCommand command);
}
