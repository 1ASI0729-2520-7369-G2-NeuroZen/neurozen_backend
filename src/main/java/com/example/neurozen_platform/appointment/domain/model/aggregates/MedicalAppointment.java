package com.example.neurozen_platform.appointment.domain.model.aggregates;

import com.example.neurozen_platform.appointment.domain.model.valueobjects.AppointmentStatus;
import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
public class MedicalAppointment extends AuditableAbstractAggregateRoot<MedicalAppointment> {
    private String appointmentDateTime;

    @Getter
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Getter
    @OneToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    private AppointmentStatus status;

    public MedicalAppointment(){

    }

    public MedicalAppointment(Patient patient, Professional professional, String appointmentDateTime) {
        this.patient = patient;
        this.professional = professional;
        this.status = AppointmentStatus.REQUESTED;
        this.appointmentDateTime = appointmentDateTime;
    }

    public void confirmAppointment() {
        this.status = AppointmentStatus.SCHEDULED;
    }

    public void completeAppointment() {
        this.status = AppointmentStatus.COMPLETED;
    }

    public void cancelAppointment() {
        this.status = AppointmentStatus.CANCELED;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public boolean isScheduled() {
        return this.status == AppointmentStatus.SCHEDULED;
    }

    public boolean isCompleted() {
        return this.status == AppointmentStatus.COMPLETED;
    }
    
    public boolean isCanceled() {
        return this.status == AppointmentStatus.CANCELED;
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    public String getAppointmentDateTime() {
        return this.appointmentDateTime;
    }
}
