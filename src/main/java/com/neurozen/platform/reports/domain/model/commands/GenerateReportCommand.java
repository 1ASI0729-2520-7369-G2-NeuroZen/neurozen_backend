package com.neurozen.platform.reports.domain.model.commands;

import com.neurozen.platform.reports.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.reports.domain.model.valueobjects.ProgressMetrics;
import com.neurozen.platform.reports.domain.model.valueobjects.ReportType;

public record GenerateReportCommand(
        EmployeeId employeeId,
        ReportType reportType,
        ProgressMetrics progressMetrics,
        String content,
        String summary
) {
}

