package com.example.neurozen_platform.appointment.application.internal.commandservices;

import org.springframework.stereotype.Service;

import com.example.neurozen_platform.appointment.domain.exceptions.PatientNotFoundException;
import com.example.neurozen_platform.appointment.domain.exceptions.ProfessionalNotFoundException;
import com.example.neurozen_platform.appointment.domain.model.commands.CancelAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.CompleteAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.ConfirmAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.RequestAppointmentCommand;
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
    public Long handle(RequestAppointmentCommand command){
        if(!patientRepository.existsById(command.patientId())){
            throw new PatientNotFoundException(command.patientId());
        }
        if(!professionalRepository.existsById(command.professionalId())){
            throw new ProfessionalNotFoundException(command.professionalId());
        }
        // Handle the request appointment command
        return null;
    }

    @Override
    public Long handle(CancelAppointmentCommand command){

    }

    @Override
    public Long handle(CompleteAppointmentCommand command){

    }

    @Override
    public Long handle(ConfirmAppointmentCommand command){

    }

}
