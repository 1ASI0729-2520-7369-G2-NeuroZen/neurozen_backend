package com.example.neurozen_platform.appointment.interfaces.rest.resources;

public record RequestMedicalAppointmentResource(Long patientId, Long professionalId, String appointmentDateTime) {

    public RequestMedicalAppointmentResource {
        if(patientId == null){
            throw new IllegalArgumentException("patientId cannot be null");
        }
        if(professionalId == null){
            throw new IllegalArgumentException("professionalId cannot be null");
        }
        if(appointmentDateTime == null || appointmentDateTime.isBlank()){
            throw new IllegalArgumentException("appointmentDateTime cannot be null or blank");
        }
    }
}
