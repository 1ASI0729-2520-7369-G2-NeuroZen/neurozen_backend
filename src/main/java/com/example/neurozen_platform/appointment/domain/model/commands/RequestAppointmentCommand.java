package com.example.neurozen_platform.appointment.domain.model.commands;

public record RequestAppointmentCommand(Long PatientId, Long ProfessionalId, String AppointmentDate) {
    public RequestAppointmentCommand {
        if(PatientId == null || PatientId<=0) {
            throw new IllegalArgumentException("PatientId cannot be null or blank");
        }
        if(ProfessionalId == null || ProfessionalId<=0) {
            throw new IllegalArgumentException("ProfessionalId cannot be null or blank");
        }
        if(AppointmentDate == null || AppointmentDate.isBlank()) {
            throw new IllegalArgumentException("AppointmentDate cannot be null or blank");
        }
    }
}
