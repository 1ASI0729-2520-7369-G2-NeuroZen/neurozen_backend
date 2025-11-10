package com.neurozen.platform.assessments.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Assessment score value object.
 * @summary
 * This value object represents an assessment score with a value between 0 and 100.
 */
@Embeddable
public record AssessmentScore(@Column(name = "score") Integer score) {
    public AssessmentScore {
        if (score == null) {
            throw new IllegalArgumentException("Assessment score cannot be null");
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Assessment score must be between 0 and 100");
        }
    }
}

