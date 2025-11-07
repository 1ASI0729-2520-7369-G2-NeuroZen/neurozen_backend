package com.example.neurozen_platform.appointment.domain.exceptions;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(Long patientId) {
        super(String.format("Patient not found: %d", patientId));
    }

}
