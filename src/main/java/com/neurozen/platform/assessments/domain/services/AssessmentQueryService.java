package com.neurozen.platform.assessments.domain.services;

import com.neurozen.platform.assessments.domain.model.aggregates.Assessment;
import com.neurozen.platform.assessments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * AssessmentQueryService
 * Service that handles assessment queries
 */
public interface AssessmentQueryService {
    /**
     * Handle a get assessment by id query
     * @param query The get assessment by id query
     * @return The assessment if found
     */
    Optional<Assessment> handle(GetAssessmentByIdQuery query);
    
    /**
     * Handle a get all assessments query
     * @param query The get all assessments query
     * @return The list of assessments
     */
    List<Assessment> handle(GetAllAssessmentsQuery query);
    
    /**
     * Handle a get all assessments by employee id query
     * @param query The get all assessments by employee id query
     * @return The list of assessments for the employee
     */
    List<Assessment> handle(GetAllAssessmentsByEmployeeIdQuery query);
}

