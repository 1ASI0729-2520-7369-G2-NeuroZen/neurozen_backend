package com.neurozen.platform.assessments.interfaces.rest.transform;

import com.neurozen.platform.assessments.domain.model.aggregates.Assessment;
import com.neurozen.platform.assessments.interfaces.rest.resources.AssessmentResource;

/**
 * Assembler to convert an Assessment entity to an AssessmentResource.
 */
public class AssessmentResourceFromEntityAssembler {
    /**
     * Converts an Assessment entity to an AssessmentResource.
     *
     * @param entity The {@link Assessment} entity to convert.
     * @return The {@link AssessmentResource} resource that results from the conversion.
     */
    public static AssessmentResource toResourceFromEntity(Assessment entity) {
        return new AssessmentResource(
                entity.getId(),
                entity.getEmployeeId().employeeId(),
                entity.getAssessmentType(),
                entity.getScore().score(),
                entity.getEmotionalState(),
                entity.getObservations(),
                entity.getRecommendations()
        );
    }
}

