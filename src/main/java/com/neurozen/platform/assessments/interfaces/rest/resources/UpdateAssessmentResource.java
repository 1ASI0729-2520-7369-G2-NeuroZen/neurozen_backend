package com.neurozen.platform.assessments.interfaces.rest.resources;

import com.neurozen.platform.assessments.domain.model.valueobjects.EmotionalState;

/**
 * Update assessment resource.
 */
public record UpdateAssessmentResource(
        Integer score,
        EmotionalState emotionalState,
        String observations,
        String recommendations
) {
}

