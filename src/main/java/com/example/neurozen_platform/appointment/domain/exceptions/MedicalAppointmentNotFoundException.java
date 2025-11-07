package com.example.neurozen_platform.appointment.domain.exceptions;

public class MedicalAppointmentNotFoundException extends RuntimeException {
    public MedicalAppointmentNotFoundException(Long appointmentId) {
        super(String.format("Medical appointment not found: %d", appointmentId));
    }

}
