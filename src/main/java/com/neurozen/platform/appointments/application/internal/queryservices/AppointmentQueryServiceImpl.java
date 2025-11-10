package com.neurozen.platform.appointments.application.internal.queryservices;

import com.neurozen.platform.appointments.domain.model.aggregates.Appointment;
import com.neurozen.platform.appointments.domain.model.queries.*;
import com.neurozen.platform.appointments.domain.services.AppointmentQueryService;
import com.neurozen.platform.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the AppointmentQueryService interface.
 * <p>This class is responsible for handling the queries related to the Appointment aggregate.</p>
 */
@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByEmployeeIdQuery query) {
        return appointmentRepository.findByEmployeeId(query.employeeId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByPsychologistIdQuery query) {
        return appointmentRepository.findByPsychologistId(query.psychologistId());
    }
}

