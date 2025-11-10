package com.neurozen.platform.appointments.interfaces.rest.transform;

import com.neurozen.platform.appointments.domain.model.commands.RescheduleAppointmentCommand;
import com.neurozen.platform.appointments.domain.model.valueobjects.AppointmentDateTime;
import com.neurozen.platform.appointments.interfaces.rest.resources.RescheduleAppointmentResource;

/**
 * Assembler to convert a RescheduleAppointmentResource to a RescheduleAppointmentCommand.
 */
public class RescheduleAppointmentCommandFromResourceAssembler {
    /**
     * Converts a RescheduleAppointmentResource to a RescheduleAppointmentCommand.
     *
     * @param appointmentId The appointment ID
     * @param resource The {@link RescheduleAppointmentResource} resource to convert.
     * @return The {@link RescheduleAppointmentCommand} command that results from the conversion.
     */
    public static RescheduleAppointmentCommand toCommandFromResource(Long appointmentId, RescheduleAppointmentResource resource) {
        return new RescheduleAppointmentCommand(
                appointmentId,
                new AppointmentDateTime(resource.newDateTime())
        );
    }
}

