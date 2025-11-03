package com.example.neurozen_platform.appointment.interfaces.rest.resources;

public record MedicalAppointmentResource(Long medicalAppointmentId, Long patientId, Long professionalId, String appointmentDateTime, String status ) {

}
