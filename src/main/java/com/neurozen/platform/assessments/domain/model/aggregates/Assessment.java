package com.neurozen.platform.assessments.domain.model.aggregates;

import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentScore;
import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentType;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmotionalState;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Assessment aggregate root.
 * @summary
 * This aggregate represents an emotional or psychological assessment for an employee.
 */
@Entity
@Getter
public class Assessment extends AuditableAbstractAggregateRoot<Assessment> {
    
    @Embedded
    private EmployeeId employeeId;
    
    private AssessmentType assessmentType;
    
    @Embedded
    private AssessmentScore score;
    
    private EmotionalState emotionalState;
    
    @Column(length = 2000)
    private String observations;
    
    @Column(length = 1000)
    private String recommendations;

    public Assessment() {
        super();
    }

    /**
     * Create a new assessment
     * @param employeeId The employee ID
     * @param assessmentType The assessment type
     * @param score The assessment score
     * @param emotionalState The emotional state
     */
    public Assessment(EmployeeId employeeId, AssessmentType assessmentType, AssessmentScore score, EmotionalState emotionalState) {
        this();
        this.employeeId = employeeId;
        this.assessmentType = assessmentType;
        this.score = score;
        this.emotionalState = emotionalState;
    }

    /**
     * Update assessment information
     * @param score The new assessment score
     * @param emotionalState The new emotional state
     * @param observations The observations
     * @param recommendations The recommendations
     */
    public void updateAssessment(AssessmentScore score, EmotionalState emotionalState, String observations, String recommendations) {
        this.score = score;
        this.emotionalState = emotionalState;
        this.observations = observations;
        this.recommendations = recommendations;
    }

    /**
     * Update observations
     * @param observations The observations
     */
    public void updateObservations(String observations) {
        this.observations = observations;
    }

    /**
     * Update recommendations
     * @param recommendations The recommendations
     */
    public void updateRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}

