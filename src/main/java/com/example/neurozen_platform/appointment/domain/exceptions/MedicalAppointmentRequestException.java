package com.example.neurozen_platform.appointment.domain.exceptions;

public class MedicalAppointmentRequestException extends RuntimeException {
    public MedicalAppointmentRequestException(String exceptionMessage) {
        super("Error while creating the appointment: %s".formatted(exceptionMessage));
    }

}
