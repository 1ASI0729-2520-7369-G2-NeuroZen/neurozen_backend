package com.example.neurozen_platform.appointment.interfaces.rest;

import com.example.neurozen_platform.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentsByPatientIdQuery;
import com.example.neurozen_platform.appointment.domain.model.queries.GetAppointmentsByProfessionalIdQuery;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentCommandService;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentQueryService;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.AppointmentResource;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.UpdateAppointmentStatusResource;
import com.example.neurozen_platform.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.example.neurozen_platform.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import com.example.neurozen_platform.appointment.interfaces.rest.transform.UpdateAppointmentStatusCommandFromResourceAssembler;
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
@RequestMapping("/api/v1/appointments")
@Tag(name = "Appointments", description = "Medical appointment management endpoints")
public class AppointmentController {

  @Autowired
  private MedicalAppointmentCommandService appointmentCommandService;

  @Autowired
  private MedicalAppointmentQueryService appointmentQueryService;

  @PostMapping
  @Operation(summary = "Create appointment", description = "Creates a new medical appointment")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Appointment created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data"),
    @ApiResponse(responseCode = "404", description = "Patient or professional not found")
  })
  public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
    var command = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
    var appointment = appointmentCommandService.handle(command);
    
    if (appointment.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    
    var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
    return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
  }

  @GetMapping("/{appointmentId}")
  @Operation(summary = "Get appointment by ID", description = "Retrieves a specific appointment by its ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Appointment found"),
    @ApiResponse(responseCode = "404", description = "Appointment not found")
  })
  public ResponseEntity<AppointmentResource> getAppointmentById(
    @Parameter(description = "Appointment ID") @PathVariable Long appointmentId) {
    var query = new GetAppointmentByIdQuery(appointmentId);
    var appointment = appointmentQueryService.handle(query);
    
    if (appointment.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
    return ResponseEntity.ok(appointmentResource);
  }

  @GetMapping
  @Operation(summary = "Get all appointments", description = "Retrieves all appointments")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully")
  })
  public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
    var query = new GetAllAppointmentsQuery();
    var appointments = appointmentQueryService.handle(query);
    var appointmentResources = appointments.stream()
      .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
      .toList();
    return ResponseEntity.ok(appointmentResources);
  }

  @GetMapping("/patient/{patientId}")
  @Operation(summary = "Get appointments by patient", description = "Retrieves all appointments for a specific patient")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully")
  })
  public ResponseEntity<List<AppointmentResource>> getAppointmentsByPatient(
    @Parameter(description = "Patient ID") @PathVariable Long patientId) {
    var query = new GetAppointmentsByPatientIdQuery(patientId);
    var appointments = appointmentQueryService.handle(query);
    var appointmentResources = appointments.stream()
      .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
      .toList();
    return ResponseEntity.ok(appointmentResources);
  }

  @GetMapping("/professional/{professionalId}")
  @Operation(summary = "Get appointments by professional", description = "Retrieves all appointments for a specific professional")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully")
  })
  public ResponseEntity<List<AppointmentResource>> getAppointmentsByProfessional(
    @Parameter(description = "Professional ID") @PathVariable Long professionalId) {
    var query = new GetAppointmentsByProfessionalIdQuery(professionalId);
    var appointments = appointmentQueryService.handle(query);
    var appointmentResources = appointments.stream()
      .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
      .toList();
    return ResponseEntity.ok(appointmentResources);
  }

  @PutMapping("/{appointmentId}/status")
  @Operation(summary = "Update appointment status", description = "Updates the status of an appointment (scheduled, completed, canceled)")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Appointment status updated successfully"),
    @ApiResponse(responseCode = "404", description = "Appointment not found"),
    @ApiResponse(responseCode = "400", description = "Invalid status")
  })
  public ResponseEntity<AppointmentResource> updateAppointmentStatus(
    @Parameter(description = "Appointment ID") @PathVariable Long appointmentId,
    @RequestBody UpdateAppointmentStatusResource resource) {
    var command = UpdateAppointmentStatusCommandFromResourceAssembler.toCommandFromResource(appointmentId, resource);
    var appointment = appointmentCommandService.handle(command);
    
    if (appointment.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
    return ResponseEntity.ok(appointmentResource);
  }
}

