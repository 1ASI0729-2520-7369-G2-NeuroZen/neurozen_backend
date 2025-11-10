package com.neurozen.platform.reports.domain.services;

import com.neurozen.platform.reports.domain.model.commands.GenerateReportCommand;

/**
 * ReportCommandService
 * Service that handles report commands
 */
public interface ReportCommandService {
    /**
     * Handle a generate report command
     * @param command The generate report command
     * @return The generated report ID
     */
    Long handle(GenerateReportCommand command);
}

