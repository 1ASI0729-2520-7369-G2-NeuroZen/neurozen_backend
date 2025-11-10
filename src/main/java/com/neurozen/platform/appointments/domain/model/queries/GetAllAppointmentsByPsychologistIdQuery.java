package com.neurozen.platform.appointments.domain.model.queries;

import com.neurozen.platform.appointments.domain.model.valueobjects.PsychologistId;

public record GetAllAppointmentsByPsychologistIdQuery(PsychologistId psychologistId) {
}

