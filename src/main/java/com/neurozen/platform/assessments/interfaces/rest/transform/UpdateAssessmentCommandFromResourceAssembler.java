package com.neurozen.platform.assessments.interfaces.rest.transform;

import com.neurozen.platform.assessments.domain.model.commands.UpdateAssessmentCommand;
import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentScore;
import com.neurozen.platform.assessments.interfaces.rest.resources.UpdateAssessmentResource;

/**
 * Assembler to convert an UpdateAssessmentResource to an UpdateAssessmentCommand.
 */
public class UpdateAssessmentCommandFromResourceAssembler {
    /**
     * Converts an UpdateAssessmentResource to an UpdateAssessmentCommand.
     *
     * @param assessmentId The assessment ID
     * @param resource The {@link UpdateAssessmentResource} resource to convert.
     * @return The {@link UpdateAssessmentCommand} command that results from the conversion.
     */
    public static UpdateAssessmentCommand toCommandFromResource(Long assessmentId, UpdateAssessmentResource resource) {
        return new UpdateAssessmentCommand(
                assessmentId,
                new AssessmentScore(resource.score()),
                resource.emotionalState(),
                resource.observations(),
                resource.recommendations()
        );
    }
}

