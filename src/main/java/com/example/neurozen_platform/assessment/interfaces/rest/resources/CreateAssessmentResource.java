package com.example.neurozen_platform.assessment.interfaces.rest.resources;

import java.util.List;
import java.util.Map;

public record CreateAssessmentResource(
  Long userId,
  Integer score,
  String level,
  List<String> recommendations,
  CategoryScoresResource categoryScores,
  Map<String, Integer> answers
) {
}

