package com.example.neurozen_platform.assessment.domain.model.aggregates;

import com.example.neurozen_platform.assessment.domain.model.valueobjects.AssessmentLevel;
import com.example.neurozen_platform.assessment.domain.model.valueobjects.CategoryScores;
import com.example.neurozen_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
public class StressAssessment extends AuditableAbstractAggregateRoot<StressAssessment> {

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Integer score; // 0-100

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AssessmentLevel level;

  @ElementCollection
  @CollectionTable(name = "assessment_recommendations", joinColumns = @JoinColumn(name = "assessment_id"))
  @Column(name = "recommendation", length = 500)
  private List<String> recommendations = new ArrayList<>();

  @Column(nullable = false)
  private LocalDateTime timestamp;

  @Embedded
  private CategoryScores categoryScores;

  @ElementCollection
  @CollectionTable(name = "assessment_answers", joinColumns = @JoinColumn(name = "assessment_id"))
  @MapKeyColumn(name = "question_id")
  @Column(name = "answer_value")
  private Map<String, Integer> answers = new HashMap<>();

  public StressAssessment() {
    this.recommendations = new ArrayList<>();
    this.answers = new HashMap<>();
    this.timestamp = LocalDateTime.now();
  }

  public StressAssessment(Long userId, Integer score, AssessmentLevel level,
                          List<String> recommendations, CategoryScores categoryScores,
                          Map<String, Integer> answers) {
    this();
    this.userId = userId;
    this.score = score;
    this.level = level;
    this.recommendations = recommendations != null ? new ArrayList<>(recommendations) : new ArrayList<>();
    this.categoryScores = categoryScores;
    this.answers = answers != null ? new HashMap<>(answers) : new HashMap<>();
    this.timestamp = LocalDateTime.now();
  }

  public void updateScore(Integer score, AssessmentLevel level) {
    this.score = score;
    this.level = level;
  }

  public void addRecommendation(String recommendation) {
    this.recommendations.add(recommendation);
  }

  public boolean isHighStress() {
    return this.level == AssessmentLevel.HIGH;
  }

  public boolean isModerateStress() {
    return this.level == AssessmentLevel.MODERATE;
  }

  public boolean isLowStress() {
    return this.level == AssessmentLevel.LOW;
  }
}

