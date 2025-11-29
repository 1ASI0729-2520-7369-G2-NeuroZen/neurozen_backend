package com.neurozen.platform.iam.domain.model.valueobjects;

/**
 * User role value object.
 * @summary
 * This enum represents the different roles a user can have in the system.
 */
public enum UserRole {
    /**
     * Employee role - can book appointments, take assessments, view reports
     */
    EMPLOYEE,
    
    /**
     * Psychologist role - can view appointments, manage patient assessments, create reports
     */
    PSYCHOLOGIST,
    
    /**
     * Admin role - full system access
     */
    ADMIN
}

