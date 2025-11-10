package com.neurozen.platform.assessments.domain.exceptions;

public class AssessmentNotFoundException extends RuntimeException {
    public AssessmentNotFoundException(Long assessmentId) {
        super("Assessment with id %s not found".formatted(assessmentId));
    }
}

