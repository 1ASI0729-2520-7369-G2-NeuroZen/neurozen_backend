package com.neurozen.platform.appointments.application.internal.commandservices;

import com.neurozen.platform.appointments.domain.model.aggregates.Appointment;
import com.neurozen.platform.appointments.domain.model.commands.*;
import com.neurozen.platform.appointments.domain.services.AppointmentCommandService;
import com.neurozen.platform.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the AppointmentCommandService interface.
 * <p>This class is responsible for handling the commands related to the Appointment aggregate.</p>
 */
@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {
        var appointment = new Appointment(
                command.employeeId(),
                command.psychologistId(),
                command.appointmentDateTime()
        );
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving appointment: %s".formatted(e.getMessage()));
        }
        return appointment.getId();
    }

    @Override
    public Optional<Appointment> handle(ConfirmAppointmentCommand command) {
        var result = appointmentRepository.findById(command.appointmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.appointmentId()));
        }
        var appointment = result.get();
        appointment.confirm();
        try {
            var confirmedAppointment = appointmentRepository.save(appointment);
            return Optional.of(confirmedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while confirming appointment: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<Appointment> handle(StartAppointmentCommand command) {
        var result = appointmentRepository.findById(command.appointmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.appointmentId()));
        }
        var appointment = result.get();
        appointment.start();
        try {
            var startedAppointment = appointmentRepository.save(appointment);
            return Optional.of(startedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while starting appointment: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<Appointment> handle(CompleteAppointmentCommand command) {
        var result = appointmentRepository.findById(command.appointmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.appointmentId()));
        }
        var appointment = result.get();
        appointment.complete(command.notes());
        try {
            var completedAppointment = appointmentRepository.save(appointment);
            return Optional.of(completedAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while completing appointment: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(CancelAppointmentCommand command) {
        var result = appointmentRepository.findById(command.appointmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.appointmentId()));
        }
        var appointment = result.get();
        appointment.cancel(command.reason());
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while cancelling appointment: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<Appointment> handle(RescheduleAppointmentCommand command) {
        var result = appointmentRepository.findById(command.appointmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Appointment with id %s not found".formatted(command.appointmentId()));
        }
        var appointment = result.get();
        appointment.reschedule(command.newDateTime());
        try {
            var rescheduledAppointment = appointmentRepository.save(appointment);
            return Optional.of(rescheduledAppointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while rescheduling appointment: %s".formatted(e.getMessage()));
        }
    }
}

