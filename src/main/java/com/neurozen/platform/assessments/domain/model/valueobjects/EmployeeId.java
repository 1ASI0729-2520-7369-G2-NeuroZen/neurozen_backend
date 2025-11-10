package com.neurozen.platform.assessments.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Employee ID value object.
 * @summary
 * This value object represents an employee identifier in the Assessments bounded context.
 */
@Embeddable
public record EmployeeId(@Column(name = "employee_id") Long employeeId) {
    public EmployeeId {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must be a positive number");
        }
    }
}

