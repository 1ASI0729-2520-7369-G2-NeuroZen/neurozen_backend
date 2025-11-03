package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.aggregates.Patient;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.PatientResource;

public class PatientResourceFromEntityAssembler {


    public static PatientResource toResourceFromEntity(Patient patient) {
        return new PatientResource(
            patient.getId(),
            patient.getProfileId()
        );
    }
}
