package com.neurozen.platform.reports.interfaces.rest.resources;

import com.neurozen.platform.reports.domain.model.valueobjects.ReportType;

/**
 * Generate report resource.
 */
public record GenerateReportResource(
        Long employeeId,
        ReportType reportType,
        Integer totalAppointments,
        Integer completedAppointments,
        Integer totalAssessments,
        Double averageScore,
        String content,
        String summary
) {
}

