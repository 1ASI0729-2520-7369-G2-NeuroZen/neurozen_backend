package com.example.neurozen_platform.appointment.application.internal.commandservices;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.commands.CreateAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.UpdateAppointmentStatusCommand;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentCommandService;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalAppointmentCommandServiceImpl implements MedicalAppointmentCommandService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private ProfessionalRepository professionalRepository;

  @Override
  public Optional<MedicalAppointment> handle(CreateAppointmentCommand command) {
    var patient = patientRepository.findById(command.patientId())
      .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + command.patientId()));

    var professional = professionalRepository.findById(command.professionalId())
      .orElseThrow(() -> new IllegalArgumentException("Professional not found with ID: " + command.professionalId()));

    var appointment = new MedicalAppointment(
      patient,
      professional,
      command.appointmentDateTime(),
      command.type(),
      command.mode(),
      command.reason()
    );

    appointmentRepository.save(appointment);
    return Optional.of(appointment);
  }

  @Override
  public Optional<MedicalAppointment> handle(UpdateAppointmentStatusCommand command) {
    var appointment = appointmentRepository.findById(command.appointmentId())
      .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + command.appointmentId()));

    switch (command.status()) {
      case SCHEDULED -> appointment.confirmAppointment();
      case COMPLETED -> appointment.completeAppointment();
      case CANCELED -> appointment.cancelAppointment();
      default -> throw new IllegalArgumentException("Invalid status: " + command.status());
    }

    appointmentRepository.save(appointment);
    return Optional.of(appointment);
  }
}

