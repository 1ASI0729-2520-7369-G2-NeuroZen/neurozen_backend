package com.neurozen.platform.reports.interfaces.rest;

import com.neurozen.platform.reports.domain.model.queries.*;
import com.neurozen.platform.reports.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.reports.domain.services.ReportCommandService;
import com.neurozen.platform.reports.domain.services.ReportQueryService;
import com.neurozen.platform.reports.interfaces.rest.resources.*;
import com.neurozen.platform.reports.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * ReportsController
 * <p>
 *     All report-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/reports", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Available Report Endpoints")
public class ReportsController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    public ReportsController(
            ReportCommandService reportCommandService,
            ReportQueryService reportQueryService
    ) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    @PostMapping
    @Operation(summary = "Generate a new report", description = "Generate a new report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report generated"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ReportResource> generateReport(@RequestBody GenerateReportResource resource) {
        var generateReportCommand = GenerateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var reportId = reportCommandService.handle(generateReportCommand);
        if (reportId == null || reportId == 0L) return ResponseEntity.badRequest().build();
        var getReportByIdQuery = new GetReportByIdQuery(reportId);
        var report = reportQueryService.handle(getReportByIdQuery);
        if (report.isEmpty()) return ResponseEntity.notFound().build();
        var reportEntity = report.get();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(reportEntity);
        return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
    }

    @GetMapping("/{reportId}")
    @Operation(summary = "Get report by id", description = "Get report by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report found"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<ReportResource> getReportById(@PathVariable Long reportId) {
        var getReportByIdQuery = new GetReportByIdQuery(reportId);
        var report = reportQueryService.handle(getReportByIdQuery);
        if (report.isEmpty()) return ResponseEntity.notFound().build();
        var reportEntity = report.get();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(reportEntity);
        return ResponseEntity.ok(reportResource);
    }

    @GetMapping
    @Operation(summary = "Get all reports", description = "Get all reports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reports found")
    })
    public ResponseEntity<List<ReportResource>> getAllReports() {
        var reports = reportQueryService.handle(new GetAllReportsQuery());
        var reportResources = reports.stream()
                .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reportResources);
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get all reports by employee id", description = "Get all reports by employee id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reports found")
    })
    public ResponseEntity<List<ReportResource>> getAllReportsByEmployeeId(@PathVariable Long employeeId) {
        var query = new GetAllReportsByEmployeeIdQuery(new EmployeeId(employeeId));
        var reports = reportQueryService.handle(query);
        var reportResources = reports.stream()
                .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reportResources);
    }
}

