package com.example.neurozen_platform.appointment.application.internal.commandservices;

import org.springframework.stereotype.Service;

import com.example.neurozen_platform.appointment.domain.exceptions.MedicalAppointmentNotFoundException;
import com.example.neurozen_platform.appointment.domain.exceptions.PatientNotFoundException;
import com.example.neurozen_platform.appointment.domain.exceptions.ProfessionalNotFoundException;
import com.example.neurozen_platform.appointment.domain.model.aggregates.MedicalAppointment;
import com.example.neurozen_platform.appointment.domain.model.commands.CancelAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.CompleteAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.ConfirmAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.RequestMedicalAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.services.MedicalAppointmentCommandService;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.MedicalAppointmentRepository;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.PatientRepository;
import com.example.neurozen_platform.appointment.infrastructure.persistence.jpa.repositories.ProfessionalRepository;

@Service
public class MedicalAppointmentCommandServiceImpl implements MedicalAppointmentCommandService {
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentCommandServiceImpl(PatientRepository patientRepository,
            ProfessionalRepository professionalRepository,
            MedicalAppointmentRepository medicalAppointmentRepository) {
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    @Override
    public Long handle(RequestMedicalAppointmentCommand command){
        if(!patientRepository.existsById(command.patientId())){
            throw new PatientNotFoundException(command.patientId());
        }
        var patient = patientRepository.findById(command.patientId()).orElseThrow(() -> new PatientNotFoundException(command.patientId()));
        if(!professionalRepository.existsById(command.professionalId())){
            throw new ProfessionalNotFoundException(command.professionalId());
        }
        var professional = professionalRepository.findById(command.professionalId()).orElseThrow(() -> new ProfessionalNotFoundException(command.professionalId()));
        try{
            var appointment = new MedicalAppointment(patient, professional, command.appointmentDate());
            medicalAppointmentRepository.save(appointment);
            return appointment.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long handle(CancelAppointmentCommand command){
        medicalAppointmentRepository.findById(command.appointmentId()).map(appointment -> {
            appointment.cancelAppointment();
            medicalAppointmentRepository.save(appointment);
            return appointment.getId();
        }).orElseThrow(() -> new MedicalAppointmentNotFoundException(command.appointmentId()));
        return null;
    }

    @Override
    public Long handle(CompleteAppointmentCommand command){
        medicalAppointmentRepository.findById(command.appointmentId()).map(appointment -> {
            appointment.completeAppointment();
            medicalAppointmentRepository.save(appointment);
            return appointment.getId();
        }).orElseThrow(() -> new MedicalAppointmentNotFoundException(command.appointmentId()));
        return null;
    }

    @Override
    public Long handle(ConfirmAppointmentCommand command){
        medicalAppointmentRepository.findById(command.appointmentId()).map(appointment -> {
            appointment.confirmAppointment();
            medicalAppointmentRepository.save(appointment);
            return appointment.getId();
        }).orElseThrow(() -> new MedicalAppointmentNotFoundException(command.appointmentId()));
        return null;
    }

}
