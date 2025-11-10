package com.neurozen.platform.reports.application.internal.commandservices;

import com.neurozen.platform.reports.domain.model.aggregates.Report;
import com.neurozen.platform.reports.domain.model.commands.GenerateReportCommand;
import com.neurozen.platform.reports.domain.services.ReportCommandService;
import com.neurozen.platform.reports.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ReportCommandService interface.
 * <p>This class is responsible for handling the commands related to the Report aggregate.</p>
 */
@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Long handle(GenerateReportCommand command) {
        var report = new Report(
                command.employeeId(),
                command.reportType(),
                command.progressMetrics(),
                command.content(),
                command.summary()
        );
        try {
            reportRepository.save(report);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving report: %s".formatted(e.getMessage()));
        }
        return report.getId();
    }
}

