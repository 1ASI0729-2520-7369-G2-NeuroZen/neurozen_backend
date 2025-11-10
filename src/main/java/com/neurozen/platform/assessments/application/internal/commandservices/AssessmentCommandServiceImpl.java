package com.neurozen.platform.assessments.application.internal.commandservices;

import com.neurozen.platform.assessments.domain.model.aggregates.Assessment;
import com.neurozen.platform.assessments.domain.model.commands.CreateAssessmentCommand;
import com.neurozen.platform.assessments.domain.model.commands.UpdateAssessmentCommand;
import com.neurozen.platform.assessments.domain.services.AssessmentCommandService;
import com.neurozen.platform.assessments.infrastructure.persistence.jpa.repositories.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the AssessmentCommandService interface.
 * <p>This class is responsible for handling the commands related to the Assessment aggregate.</p>
 */
@Service
public class AssessmentCommandServiceImpl implements AssessmentCommandService {
    private final AssessmentRepository assessmentRepository;

    public AssessmentCommandServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public Long handle(CreateAssessmentCommand command) {
        var assessment = new Assessment(
                command.employeeId(),
                command.assessmentType(),
                command.score(),
                command.emotionalState()
        );
        assessment.updateObservations(command.observations());
        assessment.updateRecommendations(command.recommendations());
        try {
            assessmentRepository.save(assessment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving assessment: %s".formatted(e.getMessage()));
        }
        return assessment.getId();
    }

    @Override
    public Optional<Assessment> handle(UpdateAssessmentCommand command) {
        var result = assessmentRepository.findById(command.assessmentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Assessment with id %s not found".formatted(command.assessmentId()));
        }
        var assessment = result.get();
        assessment.updateAssessment(
                command.score(),
                command.emotionalState(),
                command.observations(),
                command.recommendations()
        );
        try {
            var updatedAssessment = assessmentRepository.save(assessment);
            return Optional.of(updatedAssessment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating assessment: %s".formatted(e.getMessage()));
        }
    }
}

