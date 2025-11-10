package com.neurozen.platform.reports.domain.services;

import com.neurozen.platform.reports.domain.model.aggregates.Report;
import com.neurozen.platform.reports.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * ReportQueryService
 * Service that handles report queries
 */
public interface ReportQueryService {
    /**
     * Handle a get report by id query
     * @param query The get report by id query
     * @return The report if found
     */
    Optional<Report> handle(GetReportByIdQuery query);
    
    /**
     * Handle a get all reports query
     * @param query The get all reports query
     * @return The list of reports
     */
    List<Report> handle(GetAllReportsQuery query);
    
    /**
     * Handle a get all reports by employee id query
     * @param query The get all reports by employee id query
     * @return The list of reports for the employee
     */
    List<Report> handle(GetAllReportsByEmployeeIdQuery query);
}

