package com.example.neurozen_platform.assessment.interfaces.rest;

import com.example.neurozen_platform.assessment.domain.model.commands.DeleteAssessmentCommand;
import com.example.neurozen_platform.assessment.domain.model.queries.GetAssessmentByIdQuery;
import com.example.neurozen_platform.assessment.domain.model.queries.GetAssessmentsByUserIdQuery;
import com.example.neurozen_platform.assessment.domain.model.queries.GetLatestAssessmentByUserIdQuery;
import com.example.neurozen_platform.assessment.domain.services.AssessmentCommandService;
import com.example.neurozen_platform.assessment.domain.services.AssessmentQueryService;
import com.example.neurozen_platform.assessment.interfaces.rest.resources.AssessmentResource;
import com.example.neurozen_platform.assessment.interfaces.rest.resources.CreateAssessmentResource;
import com.example.neurozen_platform.assessment.interfaces.rest.transform.AssessmentResourceFromEntityAssembler;
import com.example.neurozen_platform.assessment.interfaces.rest.transform.CreateAssessmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assessments")
@Tag(name = "Assessments", description = "Stress assessment management endpoints")
public class AssessmentController {

  @Autowired
  private AssessmentCommandService assessmentCommandService;

  @Autowired
  private AssessmentQueryService assessmentQueryService;

  @PostMapping
  @Operation(summary = "Create stress assessment", description = "Creates a new stress assessment for a user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Assessment created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data")
  })
  public ResponseEntity<AssessmentResource> createAssessment(@RequestBody CreateAssessmentResource resource) {
    var command = CreateAssessmentCommandFromResourceAssembler.toCommandFromResource(resource);
    var assessment = assessmentCommandService.handle(command);
    
    if (assessment.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    
    var assessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(assessment.get());
    return new ResponseEntity<>(assessmentResource, HttpStatus.CREATED);
  }

  @GetMapping("/{assessmentId}")
  @Operation(summary = "Get assessment by ID", description = "Retrieves a specific assessment by its ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Assessment found"),
    @ApiResponse(responseCode = "404", description = "Assessment not found")
  })
  public ResponseEntity<AssessmentResource> getAssessmentById(
    @Parameter(description = "Assessment ID") @PathVariable Long assessmentId) {
    var query = new GetAssessmentByIdQuery(assessmentId);
    var assessment = assessmentQueryService.handle(query);
    
    if (assessment.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var assessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(assessment.get());
    return ResponseEntity.ok(assessmentResource);
  }

  @GetMapping("/user/{userId}")
  @Operation(summary = "Get assessments by user ID", description = "Retrieves all assessments for a specific user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Assessments retrieved successfully")
  })
  public ResponseEntity<List<AssessmentResource>> getAssessmentsByUserId(
    @Parameter(description = "User ID") @PathVariable Long userId) {
    var query = new GetAssessmentsByUserIdQuery(userId);
    var assessments = assessmentQueryService.handle(query);
    var assessmentResources = assessments.stream()
      .map(AssessmentResourceFromEntityAssembler::toResourceFromEntity)
      .toList();
    return ResponseEntity.ok(assessmentResources);
  }

  @GetMapping("/user/{userId}/latest")
  @Operation(summary = "Get latest assessment", description = "Retrieves the most recent assessment for a user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Latest assessment found"),
    @ApiResponse(responseCode = "404", description = "No assessments found for user")
  })
  public ResponseEntity<AssessmentResource> getLatestAssessment(
    @Parameter(description = "User ID") @PathVariable Long userId) {
    var query = new GetLatestAssessmentByUserIdQuery(userId);
    var assessment = assessmentQueryService.handle(query);
    
    if (assessment.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var assessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(assessment.get());
    return ResponseEntity.ok(assessmentResource);
  }

  @DeleteMapping("/{assessmentId}")
  @Operation(summary = "Delete assessment", description = "Deletes a specific assessment")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Assessment deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Assessment not found")
  })
  public ResponseEntity<Void> deleteAssessment(
    @Parameter(description = "Assessment ID") @PathVariable Long assessmentId) {
    var command = new DeleteAssessmentCommand(assessmentId);
    assessmentCommandService.handle(command);
    return ResponseEntity.noContent().build();
  }
}

