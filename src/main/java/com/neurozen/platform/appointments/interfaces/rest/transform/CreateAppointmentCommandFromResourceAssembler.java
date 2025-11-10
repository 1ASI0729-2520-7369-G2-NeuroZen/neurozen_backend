package com.neurozen.platform.appointments.interfaces.rest.transform;

import com.neurozen.platform.appointments.domain.model.commands.CreateAppointmentCommand;
import com.neurozen.platform.appointments.domain.model.valueobjects.AppointmentDateTime;
import com.neurozen.platform.appointments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.appointments.domain.model.valueobjects.PsychologistId;
import com.neurozen.platform.appointments.interfaces.rest.resources.CreateAppointmentResource;

/**
 * Assembler to convert a CreateAppointmentResource to a CreateAppointmentCommand.
 */
public class CreateAppointmentCommandFromResourceAssembler {
    /**
     * Converts a CreateAppointmentResource to a CreateAppointmentCommand.
     *
     * @param resource The {@link CreateAppointmentResource} resource to convert.
     * @return The {@link CreateAppointmentCommand} command that results from the conversion.
     */
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                new EmployeeId(resource.employeeId()),
                new PsychologistId(resource.psychologistId()),
                new AppointmentDateTime(resource.appointmentDateTime())
        );
    }
}

