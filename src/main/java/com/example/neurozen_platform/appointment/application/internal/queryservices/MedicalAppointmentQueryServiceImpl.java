package com.example.neurozen_platform.appointment.application.internal.queryservices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.queries.GetMedicalAppointmentByPatientidAndProfessionalIdQuery;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentQueryService;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;

@Service
public class MedicalAppointmentQueryServiceImpl implements MedicalAppointmentQueryService {
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentQueryServiceImpl(MedicalAppointmentRepository medicalAppointmentRepository) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Optional<MedicalAppointment> handle(GetMedicalAppointmentByPatientidAndProfessionalIdQuery query) {
        return medicalAppointmentRepository.findByPatientIdAndProfessionalId(query.patientId(), query.professionalId());
    }
}
