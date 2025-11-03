package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.commands.CreatePatientCommand;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.CreatePatientResource;

public class CreatePatientCommandFromResourceAssembler {


    public static CreatePatientCommand toCommandFromResource(CreatePatientResource resource) {
        return new CreatePatientCommand(
            resource.firstName(),
            resource.lastName(),
            resource.email(),
            resource.phoneNumber(),
            resource.district()
        );
    }
}
