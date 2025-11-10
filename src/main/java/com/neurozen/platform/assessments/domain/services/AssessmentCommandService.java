package com.neurozen.platform.assessments.domain.services;

import com.neurozen.platform.assessments.domain.model.aggregates.Assessment;
import com.neurozen.platform.assessments.domain.model.commands.CreateAssessmentCommand;
import com.neurozen.platform.assessments.domain.model.commands.UpdateAssessmentCommand;

import java.util.Optional;

/**
 * AssessmentCommandService
 * Service that handles assessment commands
 */
public interface AssessmentCommandService {
    /**
     * Handle a create assessment command
     * @param command The create assessment command
     * @return The created assessment ID
     */
    Long handle(CreateAssessmentCommand command);
    
    /**
     * Handle an update assessment command
     * @param command The update assessment command
     * @return The updated assessment
     */
    Optional<Assessment> handle(UpdateAssessmentCommand command);
}

