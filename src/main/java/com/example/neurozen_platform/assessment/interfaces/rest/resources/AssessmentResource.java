package com.example.neurozen_platform.assessment.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record AssessmentResource(
  Long id,
  Long userId,
  Integer score,
  String level,
  List<String> recommendations,
  LocalDateTime timestamp,
  CategoryScoresResource categoryScores,
  Map<String, Integer> answers
) {
}

