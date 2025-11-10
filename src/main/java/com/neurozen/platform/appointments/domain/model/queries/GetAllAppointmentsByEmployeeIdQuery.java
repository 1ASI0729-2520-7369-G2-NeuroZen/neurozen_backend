package com.neurozen.platform.appointments.domain.model.queries;

import com.neurozen.platform.appointments.domain.model.valueobjects.EmployeeId;

public record GetAllAppointmentsByEmployeeIdQuery(EmployeeId employeeId) {
}

