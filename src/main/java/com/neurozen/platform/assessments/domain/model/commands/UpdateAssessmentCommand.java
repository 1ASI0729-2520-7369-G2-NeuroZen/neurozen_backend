package com.neurozen.platform.assessments.domain.model.commands;

import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentScore;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmotionalState;

public record UpdateAssessmentCommand(
        Long assessmentId,
        AssessmentScore score,
        EmotionalState emotionalState,
        String observations,
        String recommendations
) {
}

