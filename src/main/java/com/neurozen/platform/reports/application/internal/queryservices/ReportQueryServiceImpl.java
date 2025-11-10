package com.neurozen.platform.reports.application.internal.queryservices;

import com.neurozen.platform.reports.domain.model.aggregates.Report;
import com.neurozen.platform.reports.domain.model.queries.*;
import com.neurozen.platform.reports.domain.services.ReportQueryService;
import com.neurozen.platform.reports.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ReportQueryService interface.
 * <p>This class is responsible for handling the queries related to the Report aggregate.</p>
 */
@Service
public class ReportQueryServiceImpl implements ReportQueryService {
    private final ReportRepository reportRepository;

    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(GetReportByIdQuery query) {
        return reportRepository.findById(query.reportId());
    }

    @Override
    public List<Report> handle(GetAllReportsQuery query) {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> handle(GetAllReportsByEmployeeIdQuery query) {
        return reportRepository.findByEmployeeId(query.employeeId());
    }
}

