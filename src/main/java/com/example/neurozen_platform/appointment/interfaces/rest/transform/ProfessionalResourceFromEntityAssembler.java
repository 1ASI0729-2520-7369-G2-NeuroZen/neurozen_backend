package com.example.neurozen_platform.appointment.interfaces.rest.transform;

import com.example.neurozen_platform.appointment.domain.model.aggregates.Professional;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.ProfessionalResource;

public class ProfessionalResourceFromEntityAssembler {

    public static ProfessionalResource toResourceFromEntity(Professional professional) {
        return new ProfessionalResource(
            professional.getId(),
            professional.getProfileId()
        );
    }

}
