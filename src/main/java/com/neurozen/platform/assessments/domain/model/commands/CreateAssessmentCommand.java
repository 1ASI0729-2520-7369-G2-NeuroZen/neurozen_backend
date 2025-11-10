package com.neurozen.platform.assessments.domain.model.commands;

import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentScore;
import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentType;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmotionalState;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmployeeId;

public record CreateAssessmentCommand(
        EmployeeId employeeId,
        AssessmentType assessmentType,
        AssessmentScore score,
        EmotionalState emotionalState,
        String observations,
        String recommendations
) {
}

