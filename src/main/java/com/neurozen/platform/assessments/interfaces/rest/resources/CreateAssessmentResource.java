package com.neurozen.platform.assessments.interfaces.rest.resources;

import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentType;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmotionalState;

/**
 * Create assessment resource.
 */
public record CreateAssessmentResource(
        Long employeeId,
        AssessmentType assessmentType,
        Integer score,
        EmotionalState emotionalState,
        String observations,
        String recommendations
) {
}

