package com.neurozen.platform.assessments.application.internal.queryservices;

import com.neurozen.platform.assessments.domain.model.aggregates.Assessment;
import com.neurozen.platform.assessments.domain.model.queries.*;
import com.neurozen.platform.assessments.domain.services.AssessmentQueryService;
import com.neurozen.platform.assessments.infrastructure.persistence.jpa.repositories.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the AssessmentQueryService interface.
 * <p>This class is responsible for handling the queries related to the Assessment aggregate.</p>
 */
@Service
public class AssessmentQueryServiceImpl implements AssessmentQueryService {
    private final AssessmentRepository assessmentRepository;

    public AssessmentQueryServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public Optional<Assessment> handle(GetAssessmentByIdQuery query) {
        return assessmentRepository.findById(query.assessmentId());
    }

    @Override
    public List<Assessment> handle(GetAllAssessmentsQuery query) {
        return assessmentRepository.findAll();
    }

    @Override
    public List<Assessment> handle(GetAllAssessmentsByEmployeeIdQuery query) {
        return assessmentRepository.findByEmployeeId(query.employeeId());
    }
}

