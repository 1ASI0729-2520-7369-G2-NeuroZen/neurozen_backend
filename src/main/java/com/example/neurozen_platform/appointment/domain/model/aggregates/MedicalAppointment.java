package com.example.neurozen_platform.appointment.domain.model.aggregates;

import com.example.neurozen_platform.appointment.domain.model.valueobjects.AppointmentStatus;
import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MedicalAppointment extends AuditableAbstractAggregateRoot<MedicalAppointment> {

    @Column(nullable = false)
    private String appointmentDateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    private String type; // e.g., "consultation", "therapy"

    private String mode; // e.g., "online", "in-person"

    private String reason;

    public MedicalAppointment(){

    }

    public MedicalAppointment(Patient patient, Professional professional, String appointmentDateTime,
                              String type, String mode, String reason) {
        this.patient = patient;
        this.professional = professional;
        this.appointmentDateTime = appointmentDateTime;
        this.type = type;
        this.mode = mode;
        this.reason = reason;
        this.status = AppointmentStatus.REQUESTED;
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
