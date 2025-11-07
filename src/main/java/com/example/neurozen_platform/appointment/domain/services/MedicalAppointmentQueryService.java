package com.example.neurozen_platform.appointment.domain.services;

import java.util.Optional;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.queries.GetMedicalAppointmentByPatientidAndProfessionalIdQuery;

public interface MedicalAppointmentQueryService {
    
    Optional<MedicalAppointment> handle(GetMedicalAppointmentByPatientidAndProfessionalIdQuery query);
}
