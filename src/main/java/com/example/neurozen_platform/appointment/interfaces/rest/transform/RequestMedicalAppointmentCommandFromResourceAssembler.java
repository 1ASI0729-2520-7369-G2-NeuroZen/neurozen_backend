package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.commands.RequestMedicalAppointmentCommand;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.RequestMedicalAppointmentResource;

public class RequestMedicalAppointmentCommandFromResourceAssembler {


    public static RequestMedicalAppointmentCommand toCommandFromResource(RequestMedicalAppointmentResource resource) {
        return new RequestMedicalAppointmentCommand(
                resource.patientId(),
                resource.professionalId(),
                resource.appointmentDateTime()
        );
    }
}
