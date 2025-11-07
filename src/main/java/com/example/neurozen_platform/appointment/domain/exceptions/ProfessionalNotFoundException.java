package com.example.neurozen_platform.appointment.domain.exceptions;

public class ProfessionalNotFoundException extends RuntimeException {
    public ProfessionalNotFoundException(Long professionalId) {
        super(String.format("Professional not found: %d", professionalId));
    }

}
