package com.neurozen.platform.appointments.domain.model.commands;

import com.neurozen.platform.appointments.domain.model.valueobjects.AppointmentDateTime;
import com.neurozen.platform.appointments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.appointments.domain.model.valueobjects.PsychologistId;

public record CreateAppointmentCommand(
        EmployeeId employeeId,
        PsychologistId psychologistId,
        AppointmentDateTime appointmentDateTime
) {
}

