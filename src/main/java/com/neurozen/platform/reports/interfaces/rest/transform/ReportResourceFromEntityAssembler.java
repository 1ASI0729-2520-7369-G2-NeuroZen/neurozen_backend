package com.neurozen.platform.reports.interfaces.rest.transform;

import com.neurozen.platform.reports.domain.model.aggregates.Report;
import com.neurozen.platform.reports.interfaces.rest.resources.ReportResource;

/**
 * Assembler to convert a Report entity to a ReportResource.
 */
public class ReportResourceFromEntityAssembler {
    /**
     * Converts a Report entity to a ReportResource.
     *
     * @param entity The {@link Report} entity to convert.
     * @return The {@link ReportResource} resource that results from the conversion.
     */
    public static ReportResource toResourceFromEntity(Report entity) {
        return new ReportResource(
                entity.getId(),
                entity.getEmployeeId().employeeId(),
                entity.getReportType(),
                entity.getProgressMetrics().totalAppointments(),
                entity.getProgressMetrics().completedAppointments(),
                entity.getProgressMetrics().totalAssessments(),
                entity.getProgressMetrics().averageScore(),
                entity.getContent(),
                entity.getSummary(),
                entity.getReportDate()
        );
    }
}

