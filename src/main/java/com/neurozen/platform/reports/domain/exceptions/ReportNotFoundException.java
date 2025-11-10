package com.neurozen.platform.reports.domain.exceptions;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(Long reportId) {
        super("Report with id %s not found".formatted(reportId));
    }
}

