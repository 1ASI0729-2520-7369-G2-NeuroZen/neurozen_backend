package com.neurozen.platform.appointments.interfaces.rest;

import com.neurozen.platform.appointments.domain.model.commands.*;
import com.neurozen.platform.appointments.domain.model.queries.*;
import com.neurozen.platform.appointments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.appointments.domain.model.valueobjects.PsychologistId;
import com.neurozen.platform.appointments.domain.services.AppointmentCommandService;
import com.neurozen.platform.appointments.domain.services.AppointmentQueryService;
import com.neurozen.platform.appointments.interfaces.rest.resources.*;
import com.neurozen.platform.appointments.interfaces.rest.transform.*;
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
 * AppointmentsController
 * <p>
 *     All appointment-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Available Appointment Endpoints")
public class AppointmentsController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentsController(
            AppointmentCommandService appointmentCommandService,
            AppointmentQueryService appointmentQueryService
    ) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new appointment", description = "Create a new appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointmentId = appointmentCommandService.handle(createAppointmentCommand);
        if (appointmentId == null || appointmentId == 0L) return ResponseEntity.badRequest().build();
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentEntity = appointment.get();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointmentEntity);
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{appointmentId}")
    @Operation(summary = "Get appointment by id", description = "Get appointment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment found"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentEntity = appointment.get();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointmentEntity);
        return ResponseEntity.ok(appointmentResource);
    }

    @GetMapping
    @Operation(summary = "Get all appointments", description = "Get all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found")
    })
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var appointments = appointmentQueryService.handle(new GetAllAppointmentsQuery());
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get all appointments by employee id", description = "Get all appointments by employee id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found")
    })
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentsByEmployeeId(@PathVariable Long employeeId) {
        var query = new GetAllAppointmentsByEmployeeIdQuery(new EmployeeId(employeeId));
        var appointments = appointmentQueryService.handle(query);
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/psychologist/{psychologistId}")
    @Operation(summary = "Get all appointments by psychologist id", description = "Get all appointments by psychologist id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found")
    })
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentsByPsychologistId(@PathVariable Long psychologistId) {
        var query = new GetAllAppointmentsByPsychologistIdQuery(new PsychologistId(psychologistId));
        var appointments = appointmentQueryService.handle(query);
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @PostMapping("/{appointmentId}/confirm")
    @Operation(summary = "Confirm appointment", description = "Confirm appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment confirmed"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> confirmAppointment(@PathVariable Long appointmentId) {
        var confirmCommand = new ConfirmAppointmentCommand(appointmentId);
        var appointment = appointmentCommandService.handle(confirmCommand);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @PostMapping("/{appointmentId}/start")
    @Operation(summary = "Start appointment", description = "Start appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment started"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> startAppointment(@PathVariable Long appointmentId) {
        var startCommand = new StartAppointmentCommand(appointmentId);
        var appointment = appointmentCommandService.handle(startCommand);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @PostMapping("/{appointmentId}/complete")
    @Operation(summary = "Complete appointment", description = "Complete appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment completed"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> completeAppointment(
            @PathVariable Long appointmentId,
            @RequestBody CompleteAppointmentResource resource
    ) {
        var completeCommand = new CompleteAppointmentCommand(appointmentId, resource.notes());
        var appointment = appointmentCommandService.handle(completeCommand);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @PostMapping("/{appointmentId}/cancel")
    @Operation(summary = "Cancel appointment", description = "Cancel appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment cancelled"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<?> cancelAppointment(
            @PathVariable Long appointmentId,
            @RequestBody CancelAppointmentResource resource
    ) {
        var cancelCommand = new CancelAppointmentCommand(appointmentId, resource.reason());
        appointmentCommandService.handle(cancelCommand);
        return ResponseEntity.ok("Appointment with given id successfully cancelled");
    }

    @PutMapping("/{appointmentId}/reschedule")
    @Operation(summary = "Reschedule appointment", description = "Reschedule appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment rescheduled"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    public ResponseEntity<AppointmentResource> rescheduleAppointment(
            @PathVariable Long appointmentId,
            @RequestBody RescheduleAppointmentResource resource
    ) {
        var rescheduleCommand = RescheduleAppointmentCommandFromResourceAssembler.toCommandFromResource(appointmentId, resource);
        var appointment = appointmentCommandService.handle(rescheduleCommand);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }
}

