package com.example.neurozen_platform.assessment.interfaces.rest.transform;

import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import com.example.neurozen_platform.assessment.interfaces.rest.resources.AssessmentResource;
import com.example.neurozen_platform.assessment.interfaces.rest.resources.CategoryScoresResource;

public class AssessmentResourceFromEntityAssembler {
  public static AssessmentResource toResourceFromEntity(StressAssessment entity) {
    var categoryScoresResource = new CategoryScoresResource(
      entity.getCategoryScores().getWorkScore(),
      entity.getCategoryScores().getSleepScore(),
      entity.getCategoryScores().getPhysicalScore(),
      entity.getCategoryScores().getEmotionalScore()
    );

    return new AssessmentResource(
      entity.getId(),
      entity.getUserId(),
      entity.getScore(),
      entity.getLevel().name().toLowerCase(),
      entity.getRecommendations(),
      entity.getTimestamp(),
      categoryScoresResource,
      entity.getAnswers()
    );
  }
}

