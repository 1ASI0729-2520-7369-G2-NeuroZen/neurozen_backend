package com.neurozen.platform.reports.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Progress metrics value object.
 * @summary
 * This value object represents progress metrics for an employee.
 */
@Embeddable
public record ProgressMetrics(
        @Column(name = "total_appointments") Integer totalAppointments,
        @Column(name = "completed_appointments") Integer completedAppointments,
        @Column(name = "total_assessments") Integer totalAssessments,
        @Column(name = "average_score") Double averageScore
) {
    public ProgressMetrics {
        if (totalAppointments == null) totalAppointments = 0;
        if (completedAppointments == null) completedAppointments = 0;
        if (totalAssessments == null) totalAssessments = 0;
        if (averageScore == null) averageScore = 0.0;
        
        if (totalAppointments < 0) {
            throw new IllegalArgumentException("Total appointments cannot be negative");
        }
        if (completedAppointments < 0) {
            throw new IllegalArgumentException("Completed appointments cannot be negative");
        }
        if (totalAssessments < 0) {
            throw new IllegalArgumentException("Total assessments cannot be negative");
        }
        if (averageScore < 0 || averageScore > 100) {
            throw new IllegalArgumentException("Average score must be between 0 and 100");
        }
    }
}

