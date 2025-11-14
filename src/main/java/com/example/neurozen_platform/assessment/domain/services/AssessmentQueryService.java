package com.example.neurozen_platform.assessment.domain.services;

import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import com.example.neurozen_platform.assessment.domain.model.queries.GetAssessmentByIdQuery;
import com.example.neurozen_platform.assessment.domain.model.queries.GetAssessmentsByUserIdQuery;
import com.example.neurozen_platform.assessment.domain.model.queries.GetLatestAssessmentByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface AssessmentQueryService {
  Optional<StressAssessment> handle(GetAssessmentByIdQuery query);
  List<StressAssessment> handle(GetAssessmentsByUserIdQuery query);
  Optional<StressAssessment> handle(GetLatestAssessmentByUserIdQuery query);
}

