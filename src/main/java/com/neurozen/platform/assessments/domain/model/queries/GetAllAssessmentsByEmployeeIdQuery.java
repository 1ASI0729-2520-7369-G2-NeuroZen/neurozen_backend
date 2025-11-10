package com.neurozen.platform.assessments.domain.model.queries;

import com.neurozen.platform.assessments.domain.model.valueobjects.EmployeeId;

public record GetAllAssessmentsByEmployeeIdQuery(EmployeeId employeeId) {
}

