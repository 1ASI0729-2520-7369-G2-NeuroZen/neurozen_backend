package com.neurozen.platform.reports.domain.model.aggregates;

import com.neurozen.platform.reports.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.reports.domain.model.valueobjects.ProgressMetrics;
import com.neurozen.platform.reports.domain.model.valueobjects.ReportType;
import com.neurozen.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Report aggregate root.
 * @summary
 * This aggregate represents a report about employee progress, emotional state, appointments, or assessments.
 */
@Entity
@Getter
public class Report extends AuditableAbstractAggregateRoot<Report> {
    
    @Embedded
    private EmployeeId employeeId;
    
    private ReportType reportType;
    
    @Embedded
    private ProgressMetrics progressMetrics;
    
    @Column(length = 5000)
    private String content;
    
    private LocalDate reportDate;
    
    @Column(length = 1000)
    private String summary;

    public Report() {
        super();
        this.reportDate = LocalDate.now();
    }

    /**
     * Create a new report
     * @param employeeId The employee ID
     * @param reportType The report type
     * @param progressMetrics The progress metrics
     * @param content The report content
     * @param summary The report summary
     */
    public Report(EmployeeId employeeId, ReportType reportType, ProgressMetrics progressMetrics, String content, String summary) {
        this();
        this.employeeId = employeeId;
        this.reportType = reportType;
        this.progressMetrics = progressMetrics;
        this.content = content;
        this.summary = summary;
    }

    /**
     * Update report content
     * @param content The report content
     * @param summary The report summary
     */
    public void updateContent(String content, String summary) {
        this.content = content;
        this.summary = summary;
    }

    /**
     * Update progress metrics
     * @param progressMetrics The progress metrics
     */
    public void updateProgressMetrics(ProgressMetrics progressMetrics) {
        this.progressMetrics = progressMetrics;
    }
}

