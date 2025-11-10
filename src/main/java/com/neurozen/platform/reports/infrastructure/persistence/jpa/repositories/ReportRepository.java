package com.neurozen.platform.reports.infrastructure.persistence.jpa.repositories;

import com.neurozen.platform.reports.domain.model.aggregates.Report;
import com.neurozen.platform.reports.domain.model.valueobjects.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Report repository
 * <p>This interface is used to interact with the database and perform CRUD and business command and query supporting operations on the Report entity.</p>
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    /**
     * Find all reports by employee ID
     * @param employeeId The employee ID
     * @return List of reports for the employee
     */
    List<Report> findByEmployeeId(EmployeeId employeeId);
}

