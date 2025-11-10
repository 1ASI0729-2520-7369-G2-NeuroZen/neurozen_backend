package com.neurozen.platform.reports.interfaces.rest.resources;

import com.neurozen.platform.reports.domain.model.valueobjects.ReportType;

import java.time.LocalDate;

/**
 * Report resource.
 */
public record ReportResource(
        Long id,
        Long employeeId,
        ReportType reportType,
        Integer totalAppointments,
        Integer completedAppointments,
        Integer totalAssessments,
        Double averageScore,
        String content,
        String summary,
        LocalDate reportDate
) {
}

