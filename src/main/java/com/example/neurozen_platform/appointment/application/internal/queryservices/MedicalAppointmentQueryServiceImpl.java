package com.example.neurozen_platform.appointment.application.internal.queryservices;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentsByPatientIdQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentsByProfessionalIdQuery;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentQueryService;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalAppointmentQueryServiceImpl implements MedicalAppointmentQueryService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Override
  public Optional<MedicalAppointment> handle(GetAppointmentByIdQuery query) {
    return appointmentRepository.findById(query.appointmentId());
  }

  @Override
  public List<MedicalAppointment> handle(GetAllAppointmentsQuery query) {
    return appointmentRepository.findAll();
  }

  @Override
  public List<MedicalAppointment> handle(GetAppointmentsByPatientIdQuery query) {
    return appointmentRepository.findByPatientId(query.patientId());
  }

  @Override
  public List<MedicalAppointment> handle(GetAppointmentsByProfessionalIdQuery query) {
    return appointmentRepository.findByProfessionalId(query.professionalId());
  }
}

