package com.example.neurozen_platform.appointment.domain.model.queries;

public record GetMedicalAppointmentByPatientidAndProfessionalIdQuery(Long patientId, Long professionalId) {
    public GetMedicalAppointmentByPatientidAndProfessionalIdQuery{
        if(patientId == null || patientId<=0) {
            throw new IllegalArgumentException("patientId cannot be null or blank");
        }
        if(professionalId == null || professionalId<=0) {
            throw new IllegalArgumentException("professionalId cannot be null or blank");
        }
    }
}
