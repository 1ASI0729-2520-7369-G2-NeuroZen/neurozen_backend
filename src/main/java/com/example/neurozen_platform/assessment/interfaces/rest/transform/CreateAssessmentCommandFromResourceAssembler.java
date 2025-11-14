package com.example.neurozen_platform.assessment.interfaces.rest.transform;

import com.example.neurozen_platform.assessment.domain.model.commands.CreateAssessmentCommand;
import com.example.neurozen_platform.assessment.domain.model.valueobjects.AssessmentLevel;
import com.example.neurozen_platform.assessment.domain.model.valueobjects.CategoryScores;
import com.example.neurozen_platform.assessment.interfaces.rest.resources.CreateAssessmentResource;

public class CreateAssessmentCommandFromResourceAssembler {
  public static CreateAssessmentCommand toCommandFromResource(CreateAssessmentResource resource) {
    var categoryScores = new CategoryScores(
      resource.categoryScores().work(),
      resource.categoryScores().sleep(),
      resource.categoryScores().physical(),
      resource.categoryScores().emotional()
    );

    var level = AssessmentLevel.valueOf(resource.level().toUpperCase());

    return new CreateAssessmentCommand(
      resource.userId(),
      resource.score(),
      level,
      resource.recommendations(),
      categoryScores,
      resource.answers()
    );
  }
}

