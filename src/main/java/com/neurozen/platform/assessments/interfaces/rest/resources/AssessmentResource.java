package com.neurozen.platform.assessments.interfaces.rest.resources;

import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentType;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmotionalState;

/**
 * Assessment resource.
 */
public record AssessmentResource(
        Long id,
        Long employeeId,
        AssessmentType assessmentType,
        Integer score,
        EmotionalState emotionalState,
        String observations,
        String recommendations
) {
}

