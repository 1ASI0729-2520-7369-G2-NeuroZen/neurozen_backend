package com.example.neurozen_platform.appointment.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentCommandService;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentQueryService;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.MedicalAppointmentResource;
import com.example.neurozen_platform.appointment.interfaces.rest.resources.RequestMedicalAppointmentResource;
import com.example.neurozen_platform.appointment.interfaces.rest.transform.MedicalAppointmentResourceFromEntityAssembler;
import com.example.neurozen_platform.appointment.interfaces.rest.transform.RequestMedicalAppointmentCommandFromResourceAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/v1/medical-appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Medical Appointments", description = "Endpoints for managing medical appointments")
public class MedicalAppointmentController {
    private final MedicalAppointmentCommandService medicalAppointmentCommandService;
    private final MedicalAppointmentQueryService medicalAppointmentQueryService;


    public MedicalAppointmentController(MedicalAppointmentCommandService medicalAppointmentCommandService,
                                        MedicalAppointmentQueryService medicalAppointmentQueryService) {
        this.medicalAppointmentCommandService = medicalAppointmentCommandService;
        this.medicalAppointmentQueryService = medicalAppointmentQueryService;
    }


    @PostMapping
    @Operation(summary = "Request medical appointment", description = "Endpoint to request a new medical appointment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Medical appointment requested successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Appointment not found"),
    })
    public ResponseEntity<MedicalAppointmentResource> requestMedicalAppointment(@RequestBody RequestMedicalAppointmentResource request) {
        var requestMedicalAppointmentCommand = RequestMedicalAppointmentCommandFromResourceAssembler.toCommandFromResource(request);
        var appointmentId = medicalAppointmentCommandService.handle(requestMedicalAppointmentCommand);
        if(appointmentId == null || appointmentId.equals(0L)) return ResponseEntity.badRequest().build();
        var GetMedicalAppointmentByPatientidAndProfessionalIdQuery = new com.example.neurozen_platform.appointment.domain.model.queries.GetMedicalAppointmentByPatientidAndProfessionalIdQuery(
            requestMedicalAppointmentCommand.patientId(),
            requestMedicalAppointmentCommand.professionalId());
        var appointment = medicalAppointmentQueryService.handle(GetMedicalAppointmentByPatientidAndProfessionalIdQuery);
        if(appointment.isEmpty()) return ResponseEntity.notFound().build();
        var requestAppointment = appointment.get();
        var appointmentResource = MedicalAppointmentResourceFromEntityAssembler.toResourceFromEntity(requestAppointment);
        return ResponseEntity.ok(appointmentResource);
    }
    
}
