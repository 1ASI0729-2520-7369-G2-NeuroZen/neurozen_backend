package com.example.neurozen_platform.assessment.domain.services;

import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import com.example.neurozen_platform.assessment.domain.model.commands.CreateAssessmentCommand;
import com.example.neurozen_platform.assessment.domain.model.commands.DeleteAssessmentCommand;

import java.util.Optional;

public interface AssessmentCommandService {
  Optional<StressAssessment> handle(CreateAssessmentCommand command);
  void handle(DeleteAssessmentCommand command);
}

