package com.example.neurozen_platform.assessment.infrastructure.persistence.jpa.repositories;

import com.example.neurozen_platform.assessment.domain.model.aggregates.StressAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentRepository extends JpaRepository<StressAssessment, Long> {
  List<StressAssessment> findByUserIdOrderByTimestampDesc(Long userId);
  Optional<StressAssessment> findFirstByUserIdOrderByTimestampDesc(Long userId);
}

