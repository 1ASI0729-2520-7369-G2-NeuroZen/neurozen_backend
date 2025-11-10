package com.neurozen.platform.assessments.interfaces.rest.transform;

import com.neurozen.platform.assessments.domain.model.commands.CreateAssessmentCommand;
import com.neurozen.platform.assessments.domain.model.valueobjects.AssessmentScore;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmployeeId;
import com.neurozen.platform.assessments.interfaces.rest.resources.CreateAssessmentResource;

/**
 * Assembler to convert a CreateAssessmentResource to a CreateAssessmentCommand.
 */
public class CreateAssessmentCommandFromResourceAssembler {
    /**
     * Converts a CreateAssessmentResource to a CreateAssessmentCommand.
     *
     * @param resource The {@link CreateAssessmentResource} resource to convert.
     * @return The {@link CreateAssessmentCommand} command that results from the conversion.
     */
    public static CreateAssessmentCommand toCommandFromResource(CreateAssessmentResource resource) {
        return new CreateAssessmentCommand(
                new EmployeeId(resource.employeeId()),
                resource.assessmentType(),
                new AssessmentScore(resource.score()),
                resource.emotionalState(),
                resource.observations(),
                resource.recommendations()
        );
    }
}

