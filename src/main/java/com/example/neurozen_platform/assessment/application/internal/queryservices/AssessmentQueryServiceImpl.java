package com.example.neurozen_platform.assessment.application.internal.queryservices;

import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import com.example.neurozen_platform.assessment.domain.model.queries.GetAssessmentByIdQuery;
import com.example.neurozen_platform.assessment.domain.model.queries.GetAssessmentsByUserIdQuery;
import com.example.neurozen_platform.assessment.domain.model.queries.GetLatestAssessmentByUserIdQuery;
import com.example.neurozen_platform.assessment.domain.services.AssessmentQueryService;
import com.example.neurozen_platform.assessment.infrastructure.persistence.jpa.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentQueryServiceImpl implements AssessmentQueryService {

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Override
  public Optional<StressAssessment> handle(GetAssessmentByIdQuery query) {
    return assessmentRepository.findById(query.assessmentId());
  }

  @Override
  public List<StressAssessment> handle(GetAssessmentsByUserIdQuery query) {
    return assessmentRepository.findByUserIdOrderByTimestampDesc(query.userId());
  }

  @Override
  public Optional<StressAssessment> handle(GetLatestAssessmentByUserIdQuery query) {
    return assessmentRepository.findFirstByUserIdOrderByTimestampDesc(query.userId());
  }
}

