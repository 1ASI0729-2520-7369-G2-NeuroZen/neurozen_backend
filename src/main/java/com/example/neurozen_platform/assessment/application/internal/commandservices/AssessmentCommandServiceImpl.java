package com.example.neurozen_platform.assessment.application.internal.commandservices;

import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import com.example.neurozen_platform.assessment.domain.model.commands.CreateAssessmentCommand;
import com.example.neurozen_platform.assessment.domain.model.commands.DeleteAssessmentCommand;
import com.example.neurozen_platform.assessment.domain.services.AssessmentCommandService;
import com.example.neurozen_platform.assessment.infrastructure.persistence.jpa.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentCommandServiceImpl implements AssessmentCommandService {

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Override
  public Optional<StressAssessment> handle(CreateAssessmentCommand command) {
    var assessment = new StressAssessment(
      command.userId(),
      command.score(),
      command.level(),
      command.recommendations(),
      command.categoryScores(),
      command.answers()
    );

    assessmentRepository.save(assessment);
    return Optional.of(assessment);
  }

  @Override
  public void handle(DeleteAssessmentCommand command) {
    if (!assessmentRepository.existsById(command.assessmentId())) {
      throw new IllegalArgumentException("Assessment not found with ID: " + command.assessmentId());
    }
    assessmentRepository.deleteById(command.assessmentId());
  }
}

