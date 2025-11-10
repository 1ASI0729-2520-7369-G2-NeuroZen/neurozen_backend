package com.neurozen.platform.reports.interfaces.rest.transform;

import com.neurozen.platform.reports.domain.model.commands.GenerateReportCommand;
import com.neurozen.platform.reports.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.reports.domain.model.valueobjects.ProgressMetrics;
import com.neurozen.platform.reports.interfaces.rest.resources.GenerateReportResource;

/**
 * Assembler to convert a GenerateReportResource to a GenerateReportCommand.
 */
public class GenerateReportCommandFromResourceAssembler {
    /**
     * Converts a GenerateReportResource to a GenerateReportCommand.
     *
     * @param resource The {@link GenerateReportResource} resource to convert.
     * @return The {@link GenerateReportCommand} command that results from the conversion.
     */
    public static GenerateReportCommand toCommandFromResource(GenerateReportResource resource) {
        return new GenerateReportCommand(
                new EmployeeId(resource.employeeId()),
                resource.reportType(),
                new ProgressMetrics(
                        resource.totalAppointments(),
                        resource.completedAppointments(),
                        resource.totalAssessments(),
                        resource.averageScore()
                ),
                resource.content(),
                resource.summary()
        );
    }
}

