package com.example.neurozen_platform.assessment.domain.model.commands;

import com.example.neurozen_platform.assessment.domain.model.valueobjects.AssessmentLevel;
import com.example.neurozen_platform.assessment.domain.model.valueobjects.CategoryScores;

import java.util.List;
import java.util.Map;

public record CreateAssessmentCommand(
  Long userId,
  Integer score,
  AssessmentLevel level,
  List<String> recommendations,
  CategoryScores categoryScores,
  Map<String, Integer> answers
) {
}

