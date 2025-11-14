package com.example.neurozen_platform.appointment.domain.services;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentsByPatientIdQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentsByProfessionalIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalAppointmentQueryService {
  Optional<MedicalAppointment> handle(GetAppointmentByIdQuery query);
  List<MedicalAppointment> handle(GetAllAppointmentsQuery query);
  List<MedicalAppointment> handle(GetAppointmentsByPatientIdQuery query);
  List<MedicalAppointment> handle(GetAppointmentsByProfessionalIdQuery query);
}
