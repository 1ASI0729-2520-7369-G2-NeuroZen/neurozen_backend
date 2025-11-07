package com.example.neurozen_platform.appointment.domain.model.commands;

public record RequestAppointmentCommand(Long patientId, Long professionalId, String appointmentDate) {
    public RequestAppointmentCommand {
        if(patientId == null || patientId<=0) {
            throw new IllegalArgumentException("patientId cannot be null or blank");
        }
        if(professionalId == null || professionalId<=0) {
            throw new IllegalArgumentException("professionalId cannot be null or blank");
        }
        if(appointmentDate == null || appointmentDate.isBlank()) {
            throw new IllegalArgumentException("appointmentDate cannot be null or blank");
        }
    }
}
