package com.example.neurozen_platform.appointment.domain.model.aggregates;


import com.example.neurozen_platform.appointment.domain.model.valueobjects.ProfileId;
import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Patient extends AuditableAbstractAggregateRoot<Patient> {
    
    //Este sería una columna?
    @Embedded
    private ProfileId profileId;

    public Patient(){

    }

    public Patient(Long profileId) {
       this();
       this.profileId = new ProfileId(profileId);
    }

    public Patient(ProfileId profileId){
        this();
        this.profileId = profileId;
    }


    public Long getProfileId(){
        return this.profileId.profileId();
    }
}
