package com.example.neurozen_platform.appointment.domain.model.aggregates;

import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Entity;

@Entity
public class Patient extends AuditableAbstractAggregateRoot<Patient> {
    
}
