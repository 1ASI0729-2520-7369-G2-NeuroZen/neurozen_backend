package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.MedicalAppointmentResource;

public class MedicalAppointmentResourceFromEntityAssembler {
    public static MedicalAppointmentResource toResourceFromEntity(MedicalAppointment entity){
        return new MedicalAppointmentResource(
                entity.getId(),
                entity.getPatient().getId(),
                entity.getProfessional().getId(),
                entity.getAppointmentDateTime().toString(),
                entity.getStatus()
        );
    }
}
