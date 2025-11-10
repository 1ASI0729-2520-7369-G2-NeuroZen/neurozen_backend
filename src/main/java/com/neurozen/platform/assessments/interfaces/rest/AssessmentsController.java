package com.neurozen.platform.assessments.interfaces.rest;

import com.neurozen.platform.assessments.domain.model.commands.CreateAssessmentCommand;
import com.neurozen.platform.assessments.domain.model.commands.UpdateAssessmentCommand;
import com.neurozen.platform.assessments.domain.model.queries.GetAllAssessmentsByEmployeeIdQuery;
import com.neurozen.platform.assessments.domain.model.queries.GetAllAssessmentsQuery;
import com.neurozen.platform.assessments.domain.model.queries.GetAssessmentByIdQuery;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.assessments.domain.services.AssessmentCommandService;
import com.neurozen.platform.assessments.domain.services.AssessmentQueryService;
import com.neurozen.platform.assessments.interfaces.rest.resources.*;
import com.neurozen.platform.assessments.interfaces.rest.transform.*;
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
 * AssessmentsController
 * <p>
 *     All assessment-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/assessments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Assessments", description = "Available Assessment Endpoints")
public class AssessmentsController {
    private final AssessmentCommandService assessmentCommandService;
    private final AssessmentQueryService assessmentQueryService;

    public AssessmentsController(
            AssessmentCommandService assessmentCommandService,
            AssessmentQueryService assessmentQueryService
    ) {
        this.assessmentCommandService = assessmentCommandService;
        this.assessmentQueryService = assessmentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new assessment", description = "Create a new assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assessment created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<AssessmentResource> createAssessment(@RequestBody CreateAssessmentResource resource) {
        var createAssessmentCommand = CreateAssessmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var assessmentId = assessmentCommandService.handle(createAssessmentCommand);
        if (assessmentId == null || assessmentId == 0L) return ResponseEntity.badRequest().build();
        var getAssessmentByIdQuery = new GetAssessmentByIdQuery(assessmentId);
        var assessment = assessmentQueryService.handle(getAssessmentByIdQuery);
        if (assessment.isEmpty()) return ResponseEntity.notFound().build();
        var assessmentEntity = assessment.get();
        var assessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(assessmentEntity);
        return new ResponseEntity<>(assessmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{assessmentId}")
    @Operation(summary = "Get assessment by id", description = "Get assessment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessment found"),
            @ApiResponse(responseCode = "404", description = "Assessment not found")
    })
    public ResponseEntity<AssessmentResource> getAssessmentById(@PathVariable Long assessmentId) {
        var getAssessmentByIdQuery = new GetAssessmentByIdQuery(assessmentId);
        var assessment = assessmentQueryService.handle(getAssessmentByIdQuery);
        if (assessment.isEmpty()) return ResponseEntity.notFound().build();
        var assessmentEntity = assessment.get();
        var assessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(assessmentEntity);
        return ResponseEntity.ok(assessmentResource);
    }

    @GetMapping
    @Operation(summary = "Get all assessments", description = "Get all assessments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessments found")
    })
    public ResponseEntity<List<AssessmentResource>> getAllAssessments() {
        var assessments = assessmentQueryService.handle(new GetAllAssessmentsQuery());
        var assessmentResources = assessments.stream()
                .map(AssessmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(assessmentResources);
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get all assessments by employee id", description = "Get all assessments by employee id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessments found")
    })
    public ResponseEntity<List<AssessmentResource>> getAllAssessmentsByEmployeeId(@PathVariable Long employeeId) {
        var query = new GetAllAssessmentsByEmployeeIdQuery(new EmployeeId(employeeId));
        var assessments = assessmentQueryService.handle(query);
        var assessmentResources = assessments.stream()
                .map(AssessmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(assessmentResources);
    }

    @PutMapping("/{assessmentId}")
    @Operation(summary = "Update assessment", description = "Update assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessment updated"),
            @ApiResponse(responseCode = "404", description = "Assessment not found")
    })
    public ResponseEntity<AssessmentResource> updateAssessment(
            @PathVariable Long assessmentId,
            @RequestBody UpdateAssessmentResource resource
    ) {
        var updateAssessmentCommand = UpdateAssessmentCommandFromResourceAssembler.toCommandFromResource(assessmentId, resource);
        var updatedAssessment = assessmentCommandService.handle(updateAssessmentCommand);
        if (updatedAssessment.isEmpty()) return ResponseEntity.notFound().build();
        var updatedAssessmentEntity = updatedAssessment.get();
        var updatedAssessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssessmentEntity);
        return ResponseEntity.ok(updatedAssessmentResource);
    }
}

