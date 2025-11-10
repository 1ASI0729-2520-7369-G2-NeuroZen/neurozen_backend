package com.neurozen.platform.appointments.interfaces.rest.transform;

import com.neurozen.platform.appointments.domain.model.aggregates.Appointment;
import com.neurozen.platform.appointments.interfaces.rest.resources.AppointmentResource;

/**
 * Assembler to convert an Appointment entity to an AppointmentResource.
 */
public class AppointmentResourceFromEntityAssembler {
    /**
     * Converts an Appointment entity to an AppointmentResource.
     *
     * @param entity The {@link Appointment} entity to convert.
     * @return The {@link AppointmentResource} resource that results from the conversion.
     */
    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        return new AppointmentResource(
                entity.getId(),
                entity.getEmployeeId().employeeId(),
                entity.getPsychologistId().psychologistId(),
                entity.getAppointmentDateTime().dateTime(),
                entity.getStatus(),
                entity.getNotes(),
                entity.getCancellationReason()
        );
    }
}

