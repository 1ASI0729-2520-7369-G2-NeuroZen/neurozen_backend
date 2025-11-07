package com.example.neurozen_platform.appointment.domain.services;



import com.example.neurozen_platform.appointment.domain.model.commands.CancelAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.CompleteAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.ConfirmAppointmentCommand;
import com.example.neurozen_platform.appointment.domain.model.commands.RequestMedicalAppointmentCommand;


public interface MedicalAppointmentCommandService {
    
    //Handle a request medical appointment commmand
    Long handle(RequestMedicalAppointmentCommand command);

    //Handle a cancel medical appointment command
    Long handle(CancelAppointmentCommand command);

    //Handle a complete medical appointment command
    Long handle(CompleteAppointmentCommand command);

    //Handle a confirm medical appointment command
    Long handle(ConfirmAppointmentCommand command);
}
