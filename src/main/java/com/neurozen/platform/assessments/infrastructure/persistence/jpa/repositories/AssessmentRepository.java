package com.neurozen.platform.assessments.infrastructure.persistence.jpa.repositories;

import com.neurozen.platform.assessments.domain.model.aggregates.Assessment;
import com.neurozen.platform.assessments.domain.model.valueobjects.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Assessment repository
 * <p>This interface is used to interact with the database and perform CRUD and business command and query supporting operations on the Assessment entity.</p>
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    /**
     * Find all assessments by employee ID
     * @param employeeId The employee ID
     * @return List of assessments for the employee
     */
    List<Assessment> findByEmployeeId(EmployeeId employeeId);
}

